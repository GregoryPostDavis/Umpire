package io.oc.Umpire.utils;

import de.themoep.idconverter.IdMappings;
import io.oc.Umpire.Umpire;
import io.oc.Umpire.core.UmpireMap;
import io.oc.Umpire.core.UmpireMatch;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.AbstractFileHeader;
import net.lingala.zip4j.model.FileHeader;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.*;

public class MapUtils {
    public static String findMap(String mapName){
        String[] mapFiles = (new File("maps")).list();
        int bestDistance = 5;
        String bestMap = null;
        LevenshteinDistance levenshtein = new LevenshteinDistance(5);
        int distance;

        for (String mapFile : mapFiles){
            String mapFileName = mapFile.toLowerCase().split("-")[0];
            distance = levenshtein.apply(mapName, mapFileName);
            if (distance != -1 && distance <= bestDistance){
                bestMap = mapFile;
                bestDistance = distance;
            }
        }
        return bestMap;
    }
    public static World loadWorld(String worldName){
        try {
            World newWorld = createWorld(new WorldCreator(worldName));
            getServer().getWorlds().add(newWorld);
            return newWorld;
        } catch (Exception e) {
            getLogger().info("caught error while creating world: " + e);
        }
        return null;
    }
    public static String makeWorldFolder(String bestMap){
        String mapUUID = UUID.randomUUID().toString();
        String newWorldName = "world-umpire-" + mapUUID;

        try {

            ZipFile mapZip = new ZipFile("maps/" + bestMap);

            List<FileHeader> fileHeaders = mapZip.getFileHeaders();
            String worldFolder = fileHeaders.stream().findAny().map(AbstractFileHeader::getFileName).orElse("/").split("/")[0] + '/';//TODO kinda ugly mapping of optional here
            getLogger().info("Loading map at folder: " + worldFolder);
            if (worldFolder.equals("/")){
                return null;
            }
            mapZip.extractFile(worldFolder,".", newWorldName);

            getLogger().info("loaded zip");
        } catch (Exception e) {
            getLogger().info("caught error while unzipping world: " + e);
            return null;
        }
        return newWorldName;
    }
    public static Location stringToLocation(String locString, World world){
        String[] locStringSplit = locString.split(",");
        double x = Double.parseDouble(locStringSplit[0]);
        double y = Double.parseDouble(locStringSplit[1]);
        double z = Double.parseDouble(locStringSplit[2]);
        float yaw;
        if(locStringSplit.length == 3){
            yaw = 0f;
        }
        else{
            yaw = Float.parseFloat(locStringSplit[3]);
        }
        float pitch = 0;

        return new Location(world, x, y, z, yaw, pitch);
    }
    public static Material stringToMaterial(String id){
        String[] idSplit = id.split(",");
        if(idSplit.length == 1){
            IdMappings.Mapping idMapping = IdMappings.get(IdMappings.IdType.NUMERIC, idSplit[0]);
            return Material.getMaterial(idMapping.getFlatteningType());
        } else if(idSplit.length == 2){
            IdMappings.Mapping idMapping = IdMappings.get(IdMappings.IdType.NUMERIC, idSplit[0] + ":" + idSplit[1]);
            return Material.getMaterial(idMapping.getFlatteningType());
        } else{
            return null;
        }
    }
    public static int cleanOrphanedFolders(){
        int total = 0;

        File mainDir = new File(".");
        File[] worldFolders = mainDir.listFiles((dir, name) -> name.startsWith("world-umpire"));
        if(worldFolders != null){
            for(File file : worldFolders){
                UmpireMatch match = Umpire.getMatch(file.getName());
                if (match == null){
                    total++;
                    UmpireMap delMap = new UmpireMap();
                    delMap.worldName = file.getName();
                    delMap.deload();
                }
            }
        }
        return total;
    }
}
