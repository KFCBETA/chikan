<?php
    require_once("DB_config.php");
    require_once("DB_class.php");

    $db = new DB();
    $db->connect_db($_DB['host'], $_DB['username'], $_DB['password'], $_DB['dbname']);
    $result = $db->query("SELECT image FROM intNews");
    header("Content-type: image/jpeg");
	while($row = mysql_fetch_array($result))
	{
		echo $row["image"];
	}
	mysql_close($db);

?>