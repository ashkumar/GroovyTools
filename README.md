# GroovyTools
Groovy based tools:  The intent is to create essential tools in every Java developers warchest using the wonderful Groovy language.

*SearchForClass.groovy*

This class searches for a class recursively from a given directory supplied as a parameter.  It can even search for an inner class.  Some examples of usages are
     
     **groovy SearchForClass <Dir Name> <classname>**

     **groovy SearchForClass /dev/apache-tomcat-7.0.70/lib PongMessage**

           or if you know the full package name copied from the source

     **groovy SearchForClass /dev/apache-tomcat-7.0.70/lib org.apache.tomcat.websocket.pojo.Constants**

This groovy file demonstrates the versatility of the groovy closures and also the ease of using regular expressions
