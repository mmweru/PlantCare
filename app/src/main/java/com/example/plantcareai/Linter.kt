package com.example.plantcareai

import java.io.File

fun main() {
    // Define the base directory of your project
    val baseDir = File("C:\\Users\\USER\\AndroidStudioProjects\\Apple")

    // Define the directories to lint
    val directories = listOf(
        File(baseDir, "src"),
        File(baseDir, "tests")
    )

    // Define the linters to use
    val linters = mapOf(
        "ktlint" to "*.kt",
        "detekt" to "*.kt"
    )

    // Run linting for each directory and linter
    directories.forEach { directory ->
        linters.forEach { (linter, fileExtension) ->
            println("Linting ${directory.absolutePath} with $linter...")

            // Run the linter with auto-fixing if supported
            when (linter) {
                "ktlint" -> runKtlint(directory, fileExtension)
                "detekt" -> runDetekt(directory, fileExtension)
                else -> println("Unsupported linter: $linter")
            }
        }
    }

    println("Linting complete.")
}

fun runKtlint(directory: File, fileExtension: String) {
    val command = "ktlint --android --apply-to-idea ${directory.absolutePath}/$fileExtension"
    executeCommand(command)
}

fun runDetekt(directory: File, fileExtension: String) {
    val command = "detekt ${directory.absolutePath}/$fileExtension"
    executeCommand(command)
}

fun executeCommand(command: String) {
    val process = Runtime.getRuntime().exec(command)
    val exitCode = process.waitFor()
    if (exitCode == 0) {
        println("Linting successful.")
    } else {
        println("Error occurred while linting.")
    }
}
