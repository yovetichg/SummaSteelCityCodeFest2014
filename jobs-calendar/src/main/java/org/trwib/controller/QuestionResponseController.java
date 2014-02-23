package org.trwib.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trwib.dto.QuestionResponseDto;
import org.trwib.model.QuestionResponse;
import org.trwib.service.QuestionResponseService;

import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.annotation.Resource;

/**
 * Implementation of Question API
 * https://docs.google.com/a/summa-tech.com/document/d/16zwDKPPlxdmcU1dRKQilz3BRNgzjOrSnwkg7AaRiEeM/edit
 */
@Controller
public class QuestionResponseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionResponseController.class);

    @Resource
    private QuestionResponseService questionResponseService;

    /**
     * Gets the first question
     */
    @ResponseBody
    @RequestMapping(value = "/question", method = RequestMethod.GET, produces="application/json")
    public JSONPObject firstQuestion(@RequestParam("callback") final String callback) {
        LOGGER.debug("Getting first question");
        QuestionResponse first = questionResponseService.getFirst();
        return new JSONPObject(callback, new QuestionResponseDto(first));
    }

    /**
     * Gets the first question
     */
    @ResponseBody
    @RequestMapping(value = "/question/{questionId}/{yesNo}", method = RequestMethod.GET, produces="application/json")
    public JSONPObject nextQuestion(@PathVariable("questionId") Long questionId,
                                         @PathVariable("yesNo") String yesNo,
                                         @RequestParam("callback") final String callback) {
        LOGGER.debug("Getting next question: [{}] for answer [{}]", questionId, yesNo);
        boolean yesResponse = "yes".equalsIgnoreCase(yesNo);
        QuestionResponse next = questionResponseService.getNext(questionId, yesResponse);
        return new JSONPObject(callback, new QuestionResponseDto(next));
    }

}
