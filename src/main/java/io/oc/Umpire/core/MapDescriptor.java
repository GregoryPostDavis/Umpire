package io.oc.Umpire.core;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class MapDescriptor {
    public String mapName;
    public String name;
    public String version;
    public List<String> authors;
    public ZipFile zipFile;
    public Document xmlFile;
    public List<String> tags;

    public MapDescriptor(String mapName){
        this.mapName = mapName;
        this.zipFile = new ZipFile("maps/" + mapName);

        try {
            FileHeader firstDir = this.zipFile.getFileHeaders().get(0);
            String rootDir = firstDir.getFileName().split("/")[0];
            FileHeader xmlHeader = zipFile.getFileHeader(rootDir + "/autoreferee.xml");
            getLogger().info("xmlHeader: " + xmlHeader.toString());

            tags = List.of();
            try {
                FileHeader tagsHeader = zipFile.getFileHeader(rootDir + "/tags.txt");
                tags = List.of(new String(zipFile.getInputStream(tagsHeader).readAllBytes()).split("\n"));

            } catch (Exception e) {
                getLogger().info("No tags.txt file found");
            }

            SAXBuilder saxBuilder = new SAXBuilder();
            xmlFile = saxBuilder.build(zipFile.getInputStream(xmlHeader));

            Element rootElement = xmlFile.getRootElement();
            Element meta = rootElement.getChild("meta");
            name = meta.getChild("name").getText();
            version = meta.getChild("version").getText();
            List<Element> creatorTags = meta.getChild("creators").getChildren();
            authors = creatorTags.stream().map(Element::getText).toList();

            getLogger().info("Name: " + name);
            getLogger().info("Version: " + version);
            getLogger().info("Authors: " + authors);
            getLogger().info("Tags: " + tags);
            getLogger().info("ZipFile: " + zipFile);

        } catch (IOException | JDOMException e) {
            throw new RuntimeException(e);
        }
    }
}
