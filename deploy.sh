sudo apt-get install openjdk-8*
cd assign1
javac loadbalancer.java
javac serv1.java
javac serv2.java
sudo java loadbalancer
sudo java serv1
sudo java serv2

