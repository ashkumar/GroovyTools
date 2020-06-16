import org.apache.commons.io.IOUtils
import java.nio.charset.StandardCharsets
import java.io.File
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def jsonSlurper = new JsonSlurper()
Map toSend = new HashMap()
List reading = new ArrayList()

def flowFile = session.get()
if(!flowFile) return

flowFile = session.write(flowFile, { inputStream, outputStream ->
    def row = jsonSlurper.parseText(IOUtils.toString(inputStream, StandardCharsets.UTF_8))
    reading.add(row)

    toSend["records"] = reading
    toSend["operation"] = "insert"
    toSend["schema"] = "reading"
    if(row.reading <= 18){
        toSend["table"] = "low"
    } else if(row.reading >= 22){
        toSend["table"] = "high"
    } else {
        toSend["table"] = "normal"
    }
    def json = JsonOutput.toJson(toSend)
    try {
    outputStream.write(json.getBytes(StandardCharsets.UTF_8))
    } catch (Exception e){
        System.out.println(e.getMessage())
        session.transfer(flowFile, REL_FAILURE)
    }
} as StreamCallback)

//flowFile = session.putAttribute(flowFile, 'index_name', indexName)
session.transfer(flowFile,REL_SUCCESS)