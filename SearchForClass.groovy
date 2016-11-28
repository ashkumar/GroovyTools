import java.util.jar.JarFile;

if (args.length != 2) {
			System.out.println(
				"Usage: groovy SearchForClass <Dir Name> <classname> \n" +
				"eg. groovy SearchForClass /dev/apache-tomcat-7.0.70/lib PongMessage  or \n" +
				" or if you know the full package name copied from the source \n" + 
				"groovy SearchForClass /dev/apache-tomcat-7.0.70/lib org.apache.tomcat.websocket.pojo.Constants ");
            System.exit(0);
}
//Directory to search recursively
File dir = new File("${args[0]}")

def className = "${args[1]}";
def matchString


//If className contains a . then this is a full package name - must be handled differently
if (className.contains(".")){
	def packageName = className.replaceAll('\\.', '/');
	matchString = packageName + ".class"+ "\$"
} else {
	matchString = "[\\/\$]" + className + ".class"+ "\$"
}

println matchString

JarFile jFile = null;
dir.eachFileRecurse ({
		String a =  it.absolutePath
		if( a  =~ '.jar$'){
			jFile = new JarFile(it);
			jFile.entries().find ({
				if (it.toString() =~ matchString){
						println "Found ${it.toString()} in => " + jFile.name
				}
			})
		}
	})



