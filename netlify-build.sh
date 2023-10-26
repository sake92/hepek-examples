
curl -s "https://get.sdkman.io" | bash

source "$HOME/.sdkman/bin/sdkman-init.sh"

sdk install java 11
sdk use java 11

sdk install sbt 1.9.7
sdk use sbt 1.9.7

sbt hepek
