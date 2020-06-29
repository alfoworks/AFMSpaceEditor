package ru.alfomine.afmse

import javafx.application.Application
import javafx.fxml.FXMLLoader.load
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class AFMSpaceEditor : Application() {

    private val layout = "afmse.fxml"

    override fun start(primaryStage: Stage?) {
        primaryStage?.scene = Scene(load<Parent?>(AFMSpaceEditor::class.java.classLoader.getResource(layout)))

        primaryStage?.isResizable = false
        primaryStage?.title = "ALFO:MINE SpaceEditor"

        primaryStage?.sizeToScene()

        primaryStage?.show()
    }
}

fun main() {
    Application.launch(AFMSpaceEditor::class.java)
}