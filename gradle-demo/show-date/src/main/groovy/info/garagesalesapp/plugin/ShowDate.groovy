package info.garagesalesapp.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ShowDate extends DefaultTask {

    String dateMessage = '(Plugin) Current date: '

    @TaskAction
    def showDateP() {
        println ""
        println dateMessage + new Date()
        println ""
    }
}
