<?php
class GCE_Feed{
	private $feed_id = 1;
	private $feed_title = '';
	private $feed_url = 'http://www.google.com/calendar/feeds/pittsburghworks%40gmail.com/public/basic';
	private $max_events = 1000;
	private $cache_duration = 43200;
	private $date_format = '';
	private $time_format = '';
	private $timezone = '';
	private $display_opts = array();
	private $multi_day = false;
	private $feed_start = 0;
	private $feed_end = 2145916800;
	private $use_builder = true;
	private $builder = '';
	private $events = array();
	private $error = false;
	private $gmt_offset = -5;

    public function exec($url)
    {
		echo $url."<br />";

		// fetch XML
		$ch = curl_init();

		curl_setopt_array($ch, array(
			CURLOPT_URL => $url,
			CURLOPT_HEADER => true,
			CURLOPT_TIMEOUT => 200,
			CURLOPT_RETURNTRANSFER => true
		));

        $response = curl_exec($ch);

        $error = curl_error($ch);
        $result = array( 'header' => '', 
                         'body' => '', 
                         'curl_error' => '', 
                         'http_code' => '',
                         'last_url' => '');
        if ( $error != "" )
        {
            $result['curl_error'] = $error;
            return $result;
        }
        
        $header_size = curl_getinfo($ch,CURLINFO_HEADER_SIZE);
        $result['header'] = substr($response, 0, $header_size);
        $result['body'] = substr( $response, $header_size );
        $result['http_code'] = curl_getinfo($ch,CURLINFO_HTTP_CODE);
        $result['last_url'] = curl_getinfo($ch,CURLINFO_EFFECTIVE_URL);

        curl_close($ch);

        return $result;
    }

	function init() {
		require_once 'gce-event.php';

		//Break the feed URL up into its parts (scheme, host, path, query)
		$url_parts = parse_url( $this->feed_url );

		$scheme_and_host = $url_parts['scheme'] . '://' . $url_parts['host'];

		//Remove the exisitng projection from the path, and replace it with '/full-noattendees'
		$path = substr( $url_parts['path'], 0, strrpos( $url_parts['path'], '/' ) ) . '/full-noattendees';

		//Add the default parameters to the querystring (retrieving JSON, not XML)
		$query = '?alt=json&sortorder=descending&orderby=starttime';
		$query .= "&singleevents=true"; //Show recurring events

		//Append the feed specific parameters to the querystring

		$recurrence_end = strtotime("+6 month", time()); 

		$query .= '&start-min=' . date( 'Y-m-d\TH:i:s', time() + ($this->gmt_offset * 3600) );
		$query .= '&start-max=' . date( 'Y-m-d\TH:i:s', $recurrence_end + ($this->gmt_offset * 3600) );
		$query .= '&max-results=' . $this->max_events;
		$query .= '&recurrence-expansion-end=' . date( 'Y-m-d\TH:i:s', $recurrence_end + ($this->gmt_offset * 3600) );

		if ( ! empty( $this->timezone ) )
			$query .= '&ctz=' . $this->timezone;

		//Put the URL back together
		$url = $scheme_and_host . $path . $query;

		//Begin processing calendar feed
		$this->events = array();

		//Exec curl request
		$result = $this->exec($url);
		
		$raw_data = $result['body'];

		//echo $raw_data;

		//If the curl result has an error, something went wrong
		if (! $result['curl_error'] ) {
			//If response code isn't 200, something went wrong
			if ( 200 == $result['http_code'] ) {
				//Attempt to convert the returned JSON into an array
				$raw_data = json_decode( $raw_data, true );

				//If decoding was successful
				if ( ! empty( $raw_data ) ) {
					//If there are some entries (events) to process
					if ( isset( $raw_data['feed']['entry'] ) ) {
						//Loop through each event, extracting the relevant information
						foreach ( $raw_data['feed']['entry'] as $event ) {
							$id          = $event['gCal$uid']['value'];
							$title       = $event['title']['$t'];
							$description = $event['content']['$t'];
							$link        = $event['link'][0]['href'];
							$location    = $event['gd$where'][0]['valueString'];
							$start_time  = $this->iso_to_ts( $event['gd$when'][0]['startTime'] );
							$end_time    = $this->iso_to_ts( $event['gd$when'][0]['endTime'] );

							//Create a GCE_Event using the above data. Add it to the array of events
							$this->events[] = new GCE_Event( $id, $title, $description, $location, $start_time, $end_time, $link );
						}
					}

				} else {
					//json_decode failed
					$this->error = 'Some data was retrieved, but could not be parsed successfully. Please ensure your feed URL is correct.';
				}
			} else {
				//The response code wasn't 200, so generate a helpful(ish) error message depending on error code 
				switch ( $result['http_code']) {
					case 404:
						$this->error = 'The feed could not be found (404). Please ensure your feed URL is correct.';
						break;
					case 403:
						$this->error = 'Access to this feed was denied (403). Please ensure you have public sharing enabled for your calendar.';
						break;
					default:
						$this->error = sprintf('The feed data could not be retrieved. Error code: %s. Please ensure your feed URL is correct.', $result['http_code'] );
				}
			}
		}else{
			//Generate an error message from the returned WP_Error
			$this->error = $result['curl_error'] . ' Please ensure your feed URL is correct.';
		}

	}

	//Convert an ISO date/time to a UNIX timestamp
	function iso_to_ts( $iso ) {
		sscanf( $iso, "%u-%u-%uT%u:%u:%uZ", $year, $month, $day, $hour, $minute, $second );

		$time = mktime( $hour, $minute, $second, $month, $day, $year );
		$datetime = date('Y-m-d H:i:s', $time );

		return $datetime;
	}

	//Return error message, or false if no error occurred
	function error() {
		return $this->error;
	}

	//Setters

	function set_feed_id( $v ) {
		$this->feed_id = $v;
	}

	function set_feed_title( $v ) {
		$this->feed_title = $v;
	}

	function set_feed_url( $v ) {
		$this->feed_url = $v;
	}

	function set_max_events( $v ) {
		$this->max_events = $v;
	}

	function set_cache_duration( $v ) {
		$this->cache_duration = $v;
	}

	function set_date_format( $v ) {
		$this->date_format = $v;
	}

	function set_time_format( $v ) {
		$this->time_format = $v;
	}

	function set_timezone( $v ) {
		$this->timezone = $v;
	}

	function set_display_options( $v ) {
		$this->display_opts = $v;
	}

	function set_multi_day( $v ) {
		$this->multi_day = $v;
	}

	function set_feed_start( $v ) {
		$this->feed_start = $v;
	}

	function set_feed_end( $v ) {
		$this->feed_end = $v;
	}

	function set_use_builder( $v ) {
		$this->use_builder = $v;
	}

	function set_builder( $v ) {
		$this->builder = $v;
	}

	//Getters

	function get_events() {
		return $this->events;
	}

	function get_feed_id() {
		return $this->feed_id;
	}

	function get_feed_title() {
		return $this->feed_title;
	}

	function get_feed_url() {
		return $this->feed_url;
	}

	function get_date_format() {
		return $this->date_format;
	}

	function get_time_format() {
		return $this->time_format;
	}

	function get_display_options() {
		return $this->display_opts;
	}

	function get_multi_day() {
		return $this->multi_day;
	}

	function get_feed_start() {
		return $this->feed_start;
	}

	function get_feed_end() {
		return $this->feed_end;
	}

	function get_timezone() {
		return $this->timezone;
	}

	function get_use_builder() {
		return $this->use_builder;
	}

	function get_builder() {
		return $this->builder;
	}
}
?>