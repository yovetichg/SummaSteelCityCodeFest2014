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
	$mysql_pw = "";
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
		$result = mysql_query("INSERT INTO event (name, start_dt, end_dt, location, description) 
			VALUES ('$event->title','$event->start_time','$event->start_time','$event->location','$event->location')");

		if (!$result) {
			echo mysql_error()."<br />";
		}
	}

	mysql_close($conn);

	echo "Transferred ".count($events);

?>