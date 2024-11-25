package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserInterface {

    private HashMap<String, String> optionDict;
    private List<Option> options;

    public UserInterface() {
        options = new ArrayList<>();
        options.add(new Option("-h", "--help", 0));
        options.add(new Option("-f", "--feed", 1));
        options.add(new Option("-ne", "--named-entity", 1));
        options.add(new Option("-pf", "--print-feed", 0));
        options.add(new Option("-sf", "--stats-format", 1));

        optionDict = new HashMap<>();
    }

    public Config handleInput(String[] args) {
        for (int i = 0; i < args.length; i++) {
            for (Option option : options) {
                if (option.getName().equals(args[i]) || option.getLongName().equals(args[i])) {
                    if (option.getnumValues() == 0) {
                        // Opciones sin valor asociado (-pf)
                        optionDict.put(option.getName(), null);
                    } else {
                        // Opciones con valor asociado (-f, -ne, -sf)
                        if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                            optionDict.put(option.getName(), args[i + 1]);
                            i++;
                        } else {
                            if (option.getName().equals("-ne")) {
                                optionDict.put(option.getName(), "Capital"); // Valor por defecto para -ne
                            } else if (option.getName().equals("-sf")) {
                                optionDict.put(option.getName(), "cat"); // Valor por defecto para -sf
                            } else {
                                System.out.println("Invalid inputs");
                                System.exit(1);
                            }
                        }
                    }
                }
            }
        }

        // Verificar qué opciones fueron proporcionadas
        Boolean printFeed = optionDict.containsKey("-pf");
        Boolean computeNamedEntities = optionDict.containsKey("-ne");
        String feedKey = optionDict.get("-f");

        // Si no se especifica -ne, activar -pf
        if (!computeNamedEntities) {
            printFeed = true;
        }

        // Crear la configuración y asignar statsFormat por separado si es necesario
        Config config = new Config(printFeed, computeNamedEntities, feedKey);

        // Configurar statsFormat si fue proporcionado
        String statsFormat = optionDict.get("-sf");
        if (statsFormat != null) {
            config.setStatsFormat(statsFormat);
        } else if (computeNamedEntities) {
            config.setStatsFormat("cat"); // Valor por defecto para -sf si -ne está presente
        }

        return config;
    }
}