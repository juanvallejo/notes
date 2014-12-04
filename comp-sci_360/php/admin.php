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
			die("You are not currently logged in.");
		}
	}
} else {
	if(isset($_SESSION['password'])) {
		if(isset($_POST['upload'])) {
			mysqli_report(MYSQLI_REPORT_STRICT);
			try {
				$mysqli = new mysqli('127.0.0.1','uMoviesRoot',$_SESSION['password'],'movies');
				if(!mysqli_connect_errno()) {
					if(is_file($_FILES['datafile']['tmp_name']) === true) {
						$file = fopen($_FILES['datafile']['tmp_name'],'r');
						
						$object = array();
						$object["errors"] = array();
						$object["movies"] = array();
						$object["movies"]["added"] = 0;
						$object["movies"]["excluded"] = 0;
						$object["actors"] = array();
						$object["actors"]["added"] = 0;
						$object["actors"]["excluded"] = 0;
						$object["directors"] = array();
						$object["directors"]["added"] = 0;
						$object["directors"]["excluded"] = 0;
						$object["directions"] = array();
						$object["directions"]["added"] = 0;
						$object["directions"]["excluded"] = 0;
						$object["performances"] = array();
						$object["performances"]["added"] = 0;
						$object["performances"]["excluded"] = 0;
						$object["length"] = 0;

						while(!feof($file)) {
							$line = explode(", ",fgets($file));

							$type = $line[0];
							$name = $line[1];
							$attr = $line[2];
							$extra = $line[3];

							$object["directions"]["added"]++;
							$object["performances"]["added"]++;
							$object["length"]++;

							if($type == 'movie') {
								$sql = $mysqli->query("SELECT * FROM `movies` WHERE name = '$name'");
								$object["directions"]["movie"] = $attr;
								$object["performances"]["movie"] = $attr;
								if($sql->num_rows == 0) {
									if($mysqli->query("INSERT INTO `movies` (name,year) VALUES ('$name','$attr')")) {
										$object["movies"]["last_added"] = $name;
										$object["movies"]["added"]++;
									} else {
										$object['errors'][count($object['errors'])] = mysqli_error($mysqli);
									}
								} else {
									$object["movies"]["excluded"]++;
								}
							} elseif($type == 'actor' || $type == 'actress') {
								$sql = $mysqli->query("SELECT * FROM `actors` WHERE name = '$attr'");
								$object["directions"]["actor"] = $attr;
								$object["performances"]["actor"] = $attr;
								$object["performances"]["role"] = $extra;
								if($sql->num_rows == 0) {
									$gender = $type == 'actor' ? 'male' : 'female';
									try {
										$mysqli->query("INSERT INTO `actors` (name,gender) VALUES ('$attr','$gender')");
										$object["actors"]["last_added"] = $attr;
										$object["actors"]["added"]++;
										$mysqli->query("INSERT INTO `performed_in` (actor,movie,role) VALUES ('$attr','$name','$extra')");
									} catch(Exception $e) {
										$object['errors'][count($object['errors'])] = mysqli_error($mysqli);
									}
								} else {
									//to count the number of unique records, check for repeating roles
									$mysqli->query("INSERT INTO `performed_in` (actor,movie,role) VALUES ('$attr','$name','$extra')");
									$object["actors"]["excluded"]++;
								}
							} elseif($type == 'director') {
								$sql = $mysqli->query("SELECT * FROM `directors` WHERE name = '$attr'");
								$object["directions"]["director"] = $attr;
								if($sql->num_rows == 0) {
									try {
										$mysqli->query("INSERT INTO `directors` (name) VALUES ('$attr')");
										$object["directors"]["last_added"] = $attr;
										$object["directors"]["added"]++;
										$mysqli->query("INSERT INTO `directed_by` (movie,director) VALUES ('$name','$attr')");
									} catch(Exception $e) {
										$object['errors'][count($object['errors'])] = mysqli_error($mysqli);
									}
								} else {
									$mysqli->query("INSERT INTO `directed_by` (movie,director) VALUES ('$name','$attr')");
									$object["directors"]["excluded"]++;
								}
							}
						}

						fclose($file);

						$json = json_encode($object,JSON_FORCE_OBJECT);
						die($json);
					}
				}
			} catch(Exception $e) {
				die('ERR_MYSQLI_ERROR\n'.var_dump($e));
			}
		} elseif(isset($_POST['deleteAll'])) {
			mysqli_report(MYSQLI_REPORT_STRICT);
			try {
				$mysqli = new mysqli('127.0.0.1','uMoviesRoot',$_SESSION['password'],'movies');
				if(!mysqli_connect_errno()) {
					$query = $mysqli->query("SELECT * FROM `movies`");

					if($query->num_rows > 0) {
						$actors = $mysqli->query("TRUNCATE table `actors`");
						$db = $mysqli->query("TRUNCATE table `directed_by`");
						$dirs = $mysqli->query("TRUNCATE table `directors`");
						$movies = $mysqli->query("TRUNCATE table `movies`");
						$pi = $mysqli->query("TRUNCATE table `performed_in`");
					} else {
						die("success_norecords");
					}

					if($actors && $db && $dirs && $movies && $pi) {
						die("success");
					} else {
						die(mysqli_error($mysqli));
					}
				}
			} catch(Exception $e) {

			}
		} else {
			die('ERR_INVALID_COMMAND');
		}
	} else {
		die('ERR_NOT_LOGGED_IN');
	}
}
?>
