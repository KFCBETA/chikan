<?php
	header("Content-type: text/html; charset=UTF-8");
    require_once("DB_config.php");
    require_once("DB_class.php");

    $db = new DB();
    $db->connect_db($_DB['host'], $_DB['username'], $_DB['password'], $_DB['dbname']);
    $db->query("SELECT * FROM intNews");
    while($result = $db->fetch_array())
    {
        $temp[] = str_replace("\n", "\n", $result);
    }
    $output = json_encode($temp,JSON_UNESCAPED_UNICODE);
    printf($output);
?>