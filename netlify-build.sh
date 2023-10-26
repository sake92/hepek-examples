
curl -fL "https://github.com/coursier/launchers/raw/master/cs-x86_64-pc-linux.gz" | gzip -d > cs

chmod +x cs

./cs setup --jvm 11 --apps sbt-launcher --yes

source ~/.bash_profile

sbt hepek
