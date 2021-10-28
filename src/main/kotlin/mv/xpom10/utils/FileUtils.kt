package mv.xpom10.utils

import org.apache.commons.io.FileUtils
import org.jboss.forge.roaster.model.source.JavaClassSource
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors

class FileUtils {
    companion object {
        @JvmStatic
        fun readFile(file: File): String {
            return try {
                FileUtils.readFileToString(file, StandardCharsets.UTF_8)
            } catch (e: IOException) {
                throw RuntimeException("Cant read feature file '${file.absolutePath}'", e)
            }
        }

        @JvmStatic
        fun writeJavaFile(destination: Path, source: JavaClassSource) {
            try {
                val filePath = destination.resolve(source.name + ".java")
                FileUtils.write(filePath.toFile(), source.toString(), StandardCharsets.UTF_8)
            } catch (e: IOException) {
                throw RuntimeException("Error while write generated file: '$destination'", e)
            }
        }

        @JvmStatic
        fun createDirectory(outputDirectory: File) {
            if (!outputDirectory.exists()) {
                if (!outputDirectory.mkdirs()) {
                    throw RuntimeException("Cant create output directory '$outputDirectory'")
                }
            }
        }

        @JvmStatic
        fun cleanDirectory(outputDirectory: File?) {
            try {
                FileUtils.cleanDirectory(outputDirectory)
            } catch (e: IOException) {
                throw RuntimeException("Cant clean output directory '$outputDirectory'", e)
            }
        }

        @JvmStatic
        fun findFiles(featurePath: File, filenameRegex: Regex): List<Path> {
            return if (featurePath.isDirectory) {
                try {
                    Files.walk(Paths.get(featurePath.absolutePath))
                        .filter { f: Path ->
                            f.toFile().name.matches(
                                filenameRegex
                            )
                        }
                        .collect(Collectors.toList())
                } catch (e: IOException) {
                    throw RuntimeException("Cant find features in directory '$featurePath'")
                }
            } else listOf(featurePath.toPath())
        }
    }

}