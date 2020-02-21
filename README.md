# MSV2
New Stocksrin Repo


sudo yum install java-1.8.0
sudo iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080

--------
./standalone.sh --server-config=standalone.xml & disown

java -jar <name>  & disown

netstat -ltpn

java -jar -Dspring.profiles.active=aws *.jar & disown


************ GIT **********

switch to branch
------------------
$ git checkout <branch>


To delete the local branch use one of the following:
--------------------------------------
$ git branch -d branch_nam

ssh://git@bitbucket.am.tsacorp.com:7999/dim/v4.git
