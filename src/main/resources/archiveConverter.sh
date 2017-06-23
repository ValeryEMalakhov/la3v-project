#!/bin/bash
echo 424@564ftfss | sudo -S mv "$3" "/var/www/owncloud/data/archive/files/Archive/$4/$5/$1"
echo 424@564ftfss | sudo -S chown -R www-data:www-data "/var/www/owncloud/data/archive/files/Archive/$4/$5/$1"
echo 424@564ftfss | sudo -S -u www-data php /var/www/owncloud/console.php files:scan $2
echo 424@564ftfss | sudo -S -u www-data php /var/www/owncloud/console.php files:scan archive