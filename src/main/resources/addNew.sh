#!/bin/bash
echo 424@564ftfss | sudo -S chmod 777 -R /opt/tomcat/tmpFiles/
echo 424@564ftfss | sudo -S mv "/opt/tomcat/tmpFiles/$1/$2" "/var/www/owncloud/data/$1/files/$3/"
echo 424@564ftfss | sudo -S chown -R www-data:www-data "/var/www/owncloud/data/$1/files/$3/$2"
echo 424@564ftfss | sudo -S -u www-data php /var/www/owncloud/console.php files:scan $1
