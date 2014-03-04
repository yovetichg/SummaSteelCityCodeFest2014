<?php
	require_once 'gce-feed.php';


	echo "Fetching Google Calendar data...<br />";

	$feed = new GCE_Feed();
	$feed->init();

	if ($feed->error()) {
		echo $feed->error();
		return;
	}

	$events = $feed->get_events();

	echo "Connecting to database...<br />";

	$mysql_server = "localhost";
	$mysql_user = "root";
	$mysql_pw = "summarocks";
	$mysql_db = "codefest";

	$conn = mysql_connect($mysql_server, $mysql_user, $mysql_pw);

	if (!$conn) { 
		die('Could not connect to MySQL: ' . mysql_error()); 
	} 

	$db = mysql_select_db($mysql_db, $conn);

	if (!$db) {
		die('Could not connect to MySQL database: ' . mysql_error());
	}

	echo "Connected to ".$mysql_server."<br />";  

	echo "Adding events...";
	foreach ($events as $event) {
		$provider_id = rand(10001,10084);

		$result = mysql_query("INSERT INTO event (name, start_dt, end_dt, location, description, provider_id) 
			VALUES ('$event->title','$event->start_time','$event->end_time','$event->location','$event->description', $provider_id)");

/*		echo "start_time: ".$event->start_time."<br />";
		echo $event->link."<br />";*/

		if (!$result) {
			echo "event: ".mysql_error()."<br />";
		}
		else {
			$event_id = mysql_insert_id();
			$category_id = rand(10,19);

			$result = mysql_query("INSERT INTO event_category_association (event_id, event_category_id) VALUES ($event_id, $category_id)");
			if (!$result) {
				echo "event_category_assocation: ".mysql_error()."<br />";
			}
		}
		
	}

	mysql_close($conn);

	echo "Transferred ".count($events);

?>