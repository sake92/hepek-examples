# hepek-examples

## How to run?

Just run `sbt`, wait for shell to load and type `hepek`.  

It will compile sources and render corresponding files to the "target/web/public/main/" folder (`hepekTarget`).

## Static web pages

Examples that don't *explicitly* specify `relPath` usually extends `ClassPackageRelativePath`.  
It is a handy trait which uses `object`'s class and package info to determine its path.  

For example, if you have an object called `MyText` in a package `com.myorg` 
it will be rendered to a file "com/myorg/my-text.html" ("html" is default extension).

