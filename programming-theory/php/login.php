<?php
session_start();

if(isset($_POST['password'])) {
	mysqli_report(MYSQLI_REPORT_STRICT);
	try {
		$sql = new mysqli('127.0.0.1','uMoviesRoot',$_POST['password'],'movies');
		if(!mysqli_connect_errno()) {
			$_SESSION['password'] = $_POST['password'];
			die("success");
		}
	} catch(Exception $e) {
		die("Invalid password, please try again.");
	}
} elseif(isset($_POST['session'])) {
	if(isset($_SESSION['password'])) {
		if($_POST['session'] == 'get') {
			die('TRUE');
		} elseif($_POST['session'] == 'destroy') {
			session_destroy();
		}
	} else {
		die('FALSE');
	}
} else {
	die('No data was received by the server, please try again later.');
}
?>