<?php 
	echo "Приветствую, <b>".$_REQUEST['username']."</b>!<br>";
	echo "В вашем имени букв: ".strlen($_REQUEST['username']).".";
 ?>