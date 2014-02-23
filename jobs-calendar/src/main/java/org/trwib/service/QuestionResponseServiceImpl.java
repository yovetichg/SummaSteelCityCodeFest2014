package org.trwib.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trwib.model.QuestionResponse;
import org.trwib.repository.QuestionResponseRepository;

import javax.annotation.Resource;

/**
 * This implementation of the QuestionService interface communicates with the database using a Spring Data JPA repo
 * @author bgray
 */
@Transactional
@Service
public class QuestionResponseServiceImpl implements QuestionResponseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionResponseServiceImpl.class);

    @Resource
    private QuestionResponseRepository questionResponseRepository;

    @Override
    public QuestionResponse getFirst() {
        LOGGER.debug("Getting first QuestionResponse");
        return questionResponseRepository.getFirst();
    }

    @Override
    public QuestionResponse getNext(Long questionId, boolean yesResponse) {
        LOGGER.debug("Getting next QuestionResponse for question [{}] and response [{}]", questionId, yesResponse);
        return yesResponse
                ? questionResponseRepository.getNextYesResponse(questionId)
                : questionResponseRepository.getNextNoResponse(questionId);
    }
}
