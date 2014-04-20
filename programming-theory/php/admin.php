<?php
session_start();

if(isset($_GET['module'])) {
	if($_GET['module'] == 'loginForm') {
		echo '<form method="post" name="loginForm">';
		echo '<label for="password">Password</label>';
		echo '<input type="password" placeholder="password" name="password" id="password"/>';
		echo '<input type="submit" value="Login" id="submit"/>';
		echo '<p id="out" style="display:block;clear:both;width:100%;margin:10px 0 0 0;text-align:left;color:#cd3700;"></p>';
		echo '</form>';
	} else if($_GET['module'] == 'uploadForm') {
		if(isset($_SESSION['password'])) {
			echo '<h3>Uploading Data File</h3>';
			echo '<form method="post" name="uploadForm">';
			echo '<label for="password">Data file</label>';
			echo '<input type="file" name="dataFile" id="dataFile"/>';
			echo '<input type="submit" value="Upload" id="submit"/>';
			echo '<p id="out" style="display:block;clear:both;width:100%;margin:10px 0 0 0;text-align:left;color:#cd3700;"></p>';
			echo '</form>';
			echo '<h3>Deleting Information</h3>';
			echo '<input type="button" value="Delete All" name="deleteAll" id="deleteAll"/>';
		} else {
			die("Unauthorized access.");
		}
	}
} else {
	if(isset($_SESSION['password'])) {
		die('pass');
	}
}
?>