package com.realswordteam.asdust.block.ore;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.Map;
import java.util.Objects;

public class OreHarvestLevelDictionary {
    public static OreHarvestLevelDictionary oreHarvestLevelDictionary = new OreHarvestLevelDictionary();
    private final Map<String, Integer> dictionary = new Object2ObjectOpenHashMap<>();

    public void addLevelDictionary(String name, int level)
    {
        this.dictionary.put("tile.asdust." + name, level);
    }

    public int getLevelFromDictionary(String name)
    {
        for (Map.Entry<String, Integer> entry : this.dictionary.entrySet())
        {
            if (Objects.equals(name, entry.getKey()))
            {
                return entry.getValue();
            }
        }
        return 0;
    }
}
