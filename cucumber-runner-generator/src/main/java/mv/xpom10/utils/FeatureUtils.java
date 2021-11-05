package mv.xpom10.utils;

import io.cucumber.gherkin.GherkinDocumentBuilder;
import io.cucumber.gherkin.Parser;
import io.cucumber.messages.IdGenerator;
import io.cucumber.messages.Messages;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FeatureUtils {

    private final static Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].*+?^=$\\\\|,-]");

    public static List<String> getScenarios(Path feature, List<String> tags, List<String> tests) {

        Parser<Messages.GherkinDocument.Builder> parser = new Parser<>(new GherkinDocumentBuilder(new IdGenerator.Incrementing()));
        String file = FileUtils.readFile(feature.toFile());

        Messages.GherkinDocument doc = parser.parse(file).build();
        return doc.getFeature().getChildrenList().stream()
                .map(Messages.GherkinDocument.Feature.FeatureChild::getScenario)
                .filter(scenario -> !scenario.getName().isEmpty())
                .filter(scenarioHasTest(tests))
                .filter(scenarioHasTags(tags))
                .map(Messages.GherkinDocument.Feature.Scenario::getName)
                .map(name -> SPECIAL_REGEX_CHARS.matcher(name).replaceAll("\\\\$0"))
                .map(name -> name.replaceAll("<.*?>", ".*"))
                .collect(Collectors.toList());
    }

    private static Predicate<? super Messages.GherkinDocument.Feature.Scenario> scenarioHasTags(List<String> tags) {
        return scenario -> {
            if (tags.isEmpty()) {
                return true;
            }
            List<String> scenarioTags = scenario.getTagsList()
                    .stream().map(Messages.GherkinDocument.Feature.Tag::getName)
                    .collect(Collectors.toList());
            return scenarioTags.removeAll(tags);
        };
    }

    private static Predicate<? super Messages.GherkinDocument.Feature.Scenario> scenarioHasTest(List<String> tests) {
        return scenario -> {
            if (tests.isEmpty()) {
                return true;
            }
            String name = scenario.getName();
            return tests.stream().anyMatch(name::contains);
        };
    }
}
