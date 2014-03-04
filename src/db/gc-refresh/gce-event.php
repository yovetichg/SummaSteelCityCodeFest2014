<?php
class GCE_Event{
	public $id;
	public $title;
	public $description;
	public $location;
	public $start_time;
	public $end_time;
	public $link;

	function __construct( $id, $title, $description, $location, $start_time, $end_time, $link ) {
		$this->id = $id;
		$this->title = $title;
		$this->description = $description;
		$this->location = $location;
		$this->start_time = $start_time;
		$this->end_time = $end_time;
		$this->link = $link;

	}

	function toString() {
		return $this->title;
		//echo "id: ".$event->id." title: ".$event->title." description: ".$event->description." location: ".$event->location." start_time: ".$event->start_time." end_time: ".$event->end_time." type: ".$event->type;
	}
}
?>