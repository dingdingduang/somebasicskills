package net.dingdingduang.somebasicskills.util.fileio;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.LevelResource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import java.nio.charset.StandardCharsets;
//import java.util.UUID;

//import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.printInGameMsg;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.*;
import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.*;

public class FileReadWriteMethods {
//    private String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString() + "/graveyardGhoulingUUIDmapping.txt";
//    private static final String PlayerSkillDataFolderName = "sbs_playerskilldata";
//    private static final String FilePostfix = ".txt";
    private static final String Splitor = ":";
//    private static final String MC_WORLD_ROOT_FOLDER = ".";

    //serverside only
    public static void SkillUUIDFileReadFrom(MinecraftServer MCserverInstance, ServerPlayer player) {
        HashMap<String, Integer> playerSkillDataMap = getGlobalPlayerSkillID2lvlMap().get(player);

//        UUID.nameUUIDFromBytes("OfflinePlayer:<player_name>".getBytes(StandardCharsets.UTF_8));

        String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
        String playerUUID = player.getUUID().toString();
        String tempPath;

        tempPath = PlayerSkillDataFolderDirection + "/" + "sbs_playerskilldata" + "/" + playerUUID + ".txt";

        String tempLine;
        File tempFile = new File(tempPath);
        if (tempFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(tempPath))) {
//                tempLine = reader.readLine();

                if ((tempLine = reader.readLine()) != null && tempLine.equals(playerUUID)) {
                    while ((tempLine = reader.readLine()) != null) {
                        String[] keyValuePair = tempLine.split(Splitor, 2);

                        if (keyValuePair.length > 1) {
                            playerSkillDataMap.putIfAbsent(keyValuePair[0], Integer.valueOf(keyValuePair[1]));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void SkillUUIDFileWriteTo(MinecraftServer MCserverInstance, ServerPlayer player) {
        try {
            HashMap<String, Integer> playerSkillDataMap = getGlobalPlayerSkillID2lvlMap().get(player);

            String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
            String playerUUID = player.getUUID().toString();
//            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ PlayerSkillDataFolderName +"/"+ playerName+"-"+playerUUID + FilePostfix;

            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ "sbs_playerskilldata";

//            Path tempPath = Paths.get(tempPathStr);
//            Files.createDirectories(tempPath);
            Files.createDirectories(Paths.get(tempPathStr));

            tempPathStr = tempPathStr +"/"+ playerUUID + ".txt";

            FileWriter tempFileWriter = new FileWriter(tempPathStr);
            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);

            tempBufferedWriter.write(playerUUID);
            tempBufferedWriter.newLine();

            for (Map.Entry<String, Integer> entry : playerSkillDataMap.entrySet()) {
                tempBufferedWriter.write(entry.getKey() + Splitor + entry.getValue());
                tempBufferedWriter.newLine();
            }

            tempBufferedWriter.flush();
            tempBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SkillPlayernameFileReadFrom(MinecraftServer MCserverInstance, ServerPlayer player) {
        HashMap<String, Integer> playerSkillDataMap = getGlobalPlayerSkillID2lvlMap().get(player);

        String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
        String playerName = player.getName().getString();
        String tempPath;

        tempPath = PlayerSkillDataFolderDirection + "/" + "sbs_playerskilldata" + "/" + playerName + ".txt";

        String tempLine;
        File tempFile = new File(tempPath);
        if (tempFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(tempPath))) {
//                tempLine = reader.readLine();
                if ((tempLine = reader.readLine()) != null && tempLine.equals(playerName)) {
                    while ((tempLine = reader.readLine()) != null) {
                        String[] keyValuePair = tempLine.split(Splitor, 2);

                        if (keyValuePair.length > 1) {
                            playerSkillDataMap.putIfAbsent(keyValuePair[0], Integer.valueOf(keyValuePair[1]));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void SkillPlayernameFileWriteTo(MinecraftServer MCserverInstance, ServerPlayer player) {
        try {
            HashMap<String, Integer> playerSkillDataMap = getGlobalPlayerSkillID2lvlMap().get(player);

            String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
            String playerName = player.getName().getString();
//            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ PlayerSkillDataFolderName +"/"+ playerName+"-"+playerUUID + FilePostfix;

            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ "sbs_playerskilldata";

//            Path tempPath = Paths.get(tempPathStr);
//            Files.createDirectories(tempPath);
            Files.createDirectories(Paths.get(tempPathStr));

            tempPathStr = tempPathStr +"/"+ playerName + ".txt";

            FileWriter tempFileWriter = new FileWriter(tempPathStr);
            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);

            tempBufferedWriter.write(playerName);
            tempBufferedWriter.newLine();

            for (Map.Entry<String, Integer> entry : playerSkillDataMap.entrySet()) {
                if (entry.getKey().startsWith("T_")) { continue; }
                tempBufferedWriter.write(entry.getKey() + Splitor + entry.getValue());
                tempBufferedWriter.newLine();
            }

            tempBufferedWriter.flush();
            tempBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void StatusPlayerNameFileReadFrom(MinecraftServer MCserverInstance, ServerPlayer player) {
        HashMap<String, Double> statusMap = getSPlayerValue2BaseMultiplierMap().get(player);

        String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
        String playerName = player.getName().getString();
        String tempPath;

        tempPath = PlayerSkillDataFolderDirection + "/" + "sbs_playerskilldata" + "/" + playerName + "_sbstatus.txt";

        String tempLine;
        File tempFile = new File(tempPath);
        if (tempFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(tempPath))) {
//                tempLine = reader.readLine();
                if ((tempLine = reader.readLine()) != null && tempLine.equals(playerName)) {
                    while ((tempLine = reader.readLine()) != null) {
                        String[] keyValuePair = tempLine.split(Splitor, 2);

                        if (keyValuePair.length > 1) {
                            statusMap.put(keyValuePair[0], Double.parseDouble(keyValuePair[1]));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void StatusPlayerNameFileWriteTo(MinecraftServer MCserverInstance, ServerPlayer player) {
        try {
            HashMap<String, Double> statusMap = getSPlayerValue2BaseMultiplierMap().get(player);

            String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
            String playerName = player.getName().getString();
//            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ PlayerSkillDataFolderName +"/"+ playerName+"-"+playerUUID + FilePostfix;

            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ "sbs_playerskilldata";

//            Path tempPath = Paths.get(tempPathStr);
//            Files.createDirectories(tempPath);
            Files.createDirectories(Paths.get(tempPathStr));

            tempPathStr = tempPathStr +"/"+ playerName + "_sbstatus.txt";

            FileWriter tempFileWriter = new FileWriter(tempPathStr);
            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);

            tempBufferedWriter.write(playerName);
            tempBufferedWriter.newLine();

            for (Map.Entry<String, Double> entry : statusMap.entrySet()) {
                if (entry.getKey().startsWith(TEMPORARY_PREFIX)) { continue; }
                tempBufferedWriter.write(entry.getKey() + Splitor + entry.getValue());
                tempBufferedWriter.newLine();
            }

            tempBufferedWriter.flush();
            tempBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //keyboard hotkey save
//    public static void QuickslotPlayernameFileReadFrom(MinecraftServer MCserverInstance, ServerPlayer player) {
//        HashMap<Integer, String> playerQuickslotKey = getSPlayerKey2SkillID().get(player);
//        HashMap<String, Integer> playerSkillID2Key = getSPlayerSkillID2Key().get(player);
//
//        String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
//        String playerName = player.getName().getString();
//        String tempPath;
//
//        tempPath = PlayerSkillDataFolderDirection + "/" + "sbs_playerskilldata" + "/" + playerName + "_quickslot.txt";
//
//        String tempLine;
//        File tempFile = new File(tempPath);
//        if (tempFile.exists()) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(tempPath))) {
////                tempLine = reader.readLine();
//                if ((tempLine = reader.readLine()) != null && tempLine.equals(playerName)) {
//                    while ((tempLine = reader.readLine()) != null) {
//                        String[] keyValuePair = tempLine.split(Splitor, 2);
//
//                        if (keyValuePair.length > 1) {
//                            playerQuickslotKey.put(Integer.valueOf(keyValuePair[0]), keyValuePair[1]);
//                            playerSkillID2Key.put(keyValuePair[1], Integer.valueOf(keyValuePair[0]));
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void QuickslotPlayernameFileWriteTo(MinecraftServer MCserverInstance, ServerPlayer player) {
//        try {
//            HashMap<Integer, String> playerQuickslotKey = getSPlayerKey2SkillID().get(player);
//
//            String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
//            String playerName = player.getName().getString();
////            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ PlayerSkillDataFolderName +"/"+ playerName+"-"+playerUUID + FilePostfix;
//
//            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ "sbs_playerskilldata";
//
////            Path tempPath = Paths.get(tempPathStr);
////            Files.createDirectories(tempPath);
//            Files.createDirectories(Paths.get(tempPathStr));
//
//            tempPathStr = tempPathStr +"/"+ playerName + "_quickslot.txt";
//
//            FileWriter tempFileWriter = new FileWriter(tempPathStr);
//            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);
//
//            tempBufferedWriter.write(playerName);
//            tempBufferedWriter.newLine();
//
//            for (Map.Entry<Integer, String> entry : playerQuickslotKey.entrySet()) {
//                tempBufferedWriter.write(entry.getKey() + Splitor + entry.getValue());
//                tempBufferedWriter.newLine();
//            }
//
//            tempBufferedWriter.flush();
//            tempBufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void KeycomboPlayernameFileReadFrom(MinecraftServer MCserverInstance, ServerPlayer player) {
//        HashMap<ArrayList<Integer>, String> playerKeycomboList = getSPlayerKeyCombo2SkillID().get(player);
//        HashMap<String, ArrayList<Integer>> playerSkillID2KeycomboList = getSPlayerSkillID2KeyCombo().get(player);
//
//        String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
//        String playerName = player.getName().getString();
//        String tempPath;
//
//        tempPath = PlayerSkillDataFolderDirection + "/" + "sbs_playerskilldata" + "/" + playerName + "_keycombolist.txt";
//
//        String tempLine;
//        File tempFile = new File(tempPath);
//        if (tempFile.exists()) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(tempPath))) {
////                tempLine = reader.readLine();
//                if ((tempLine = reader.readLine()) != null && tempLine.equals(playerName)) {
//                    while ((tempLine = reader.readLine()) != null) {
//                        String[] keyValuePair = tempLine.split(Splitor);
//
//                        if (keyValuePair.length > 1) {
//                            ArrayList<Integer> tempArrList = new ArrayList<Integer>();
//
//                            for (int k = 0; k < keyValuePair.length-1; k++) {
//                                tempArrList.add(Integer.valueOf(keyValuePair[k]));
//                            }
//
//                            playerKeycomboList.put(tempArrList, keyValuePair[keyValuePair.length-1]);
//                            playerSkillID2KeycomboList.put(keyValuePair[keyValuePair.length-1], tempArrList);
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void KeycomboPlayernameFileWriteTo(MinecraftServer MCserverInstance, ServerPlayer player) {
//        try {
//            HashMap<ArrayList<Integer>, String> playerKeycomboList = getSPlayerKeyCombo2SkillID().get(player);
//
//            String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
//            String playerName = player.getName().getString();
////            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ PlayerSkillDataFolderName +"/"+ playerName+"-"+playerUUID + FilePostfix;
//
//            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ "sbs_playerskilldata";
//
////            Path tempPath = Paths.get(tempPathStr);
////            Files.createDirectories(tempPath);
//            Files.createDirectories(Paths.get(tempPathStr));
//
//            tempPathStr = tempPathStr +"/"+ playerName + "_keycombolist.txt";
//
//            FileWriter tempFileWriter = new FileWriter(tempPathStr);
//            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);
//
//            tempBufferedWriter.write(playerName);
//            tempBufferedWriter.newLine();
//
//            for (ArrayList<Integer> tempArrList: playerKeycomboList.keySet()) {
//                for (int keyCode: tempArrList) {
//                    tempBufferedWriter.write(keyCode + Splitor);
//                }
//
//                tempBufferedWriter.write(playerKeycomboList.get(tempArrList));
//                tempBufferedWriter.newLine();
//            }
//
//            tempBufferedWriter.flush();
//            tempBufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//config
public static void ConfigPlayernameFileReadFrom(MinecraftServer MCserverInstance, ServerPlayer player) {
    HashMap<String, HashMap<String, MethodConfigHelper>> playerConfigMap = getSPlayerConfig().get(player);

    String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
    String playerName = player.getName().getString();
    String tempPath;

    tempPath = PlayerSkillDataFolderDirection + "/" + "sbs_playerskilldata" + "/" + playerName + "_server_config_file.txt";

    String tempLine;
    File tempFile = new File(tempPath);
    MethodConfigHelper tempMethodConfigHelper;
    if (tempFile.exists()) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tempPath))) {
            while ((tempLine = reader.readLine()) != null) {
                String[] keyValuePair = tempLine.split(Splitor, 3);

                if (keyValuePair.length > 2 && getSConfig().contains(keyValuePair[0])) {
                    HashMap<String, MethodConfigHelper> tempConfigHelperMap = playerConfigMap.get(keyValuePair[0]);
                    if (tempConfigHelperMap != null && (tempMethodConfigHelper = tempConfigHelperMap.get(keyValuePair[1])) != null) {
                        tempMethodConfigHelper.setIntValue(Integer.parseInt(keyValuePair[2]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    public static void ConfigPlayernameFileWriteTo(MinecraftServer MCserverInstance, ServerPlayer player) {
        try {
            HashMap<String, HashMap<String, MethodConfigHelper>> playerConfigMap = getSPlayerConfig().get(player);

            String PlayerSkillDataFolderDirection = MCserverInstance.getWorldPath(LevelResource.ROOT).toString();
            String playerName = player.getName().getString();
//            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ PlayerSkillDataFolderName +"/"+ playerName+"-"+playerUUID + FilePostfix;

            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ "sbs_playerskilldata";

            Files.createDirectories(Paths.get(tempPathStr));

            tempPathStr = tempPathStr +"/"+ playerName + "_server_config_file.txt";

            FileWriter tempFileWriter = new FileWriter(tempPathStr);
            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);

            for (String configName: playerConfigMap.keySet()) {
                for (Map.Entry<String, MethodConfigHelper> entry: playerConfigMap.get(configName).entrySet()) {
                    tempBufferedWriter.write(configName + Splitor + entry.getKey() + Splitor + entry.getValue().getIntValue());
                    tempBufferedWriter.newLine();
                }
            }

            tempBufferedWriter.flush();
            tempBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //=======================================
    //client side only
    //skill priority
    public static void SkillPriorityPlayernameFileReadFrom() {
        HashMap<String, Integer> playerSkillID2Priority = getCPlayerSkillID2Priority();

        String PlayerSkillDataFolderDirection = "config/"+ Constants.MOD_ID+"/";
        String tempPath;

        tempPath = PlayerSkillDataFolderDirection + "/" + "sbs_playerskilldata" + "/skill_priority_setting.txt";

        String tempLine;
        File tempFile = new File(tempPath);

        if (tempFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(tempPath))) {
                while ((tempLine = reader.readLine()) != null) {
                    String[] keyValuePair = tempLine.split(Splitor, 2);

                    if (keyValuePair.length > 1) {
                        playerSkillID2Priority.put(keyValuePair[0], Integer.valueOf(keyValuePair[1]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void SkillPriorityPlayernameFileWriteTo() {
        try {
            HashMap<String, Integer> playerSkillID2Priority = getCPlayerSkillID2Priority();

            String PlayerSkillDataFolderDirection = "config/"+ Constants.MOD_ID+"/";

            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ "sbs_playerskilldata";

            Files.createDirectories(Paths.get(tempPathStr));

            tempPathStr = tempPathStr +"/skill_priority_setting.txt";

            FileWriter tempFileWriter = new FileWriter(tempPathStr);
            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);

            for (Map.Entry<String, Integer> entry : playerSkillID2Priority.entrySet()) {
                if (entry.getValue() > 0) {
                    tempBufferedWriter.write(entry.getKey() + Splitor + entry.getValue());
                    tempBufferedWriter.newLine();
                }
            }

            tempBufferedWriter.flush();
            tempBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //skill cooldown delay
    public static void SkillCooldownDelayPlayernameFileReadFrom() {
        HashMap<String, Float> playerSkillID2CooldownDelay = getCPlayerSkillID2CooldownDelay();

        String PlayerSkillDataFolderDirection = "config/"+ Constants.MOD_ID+"/";
        String tempPath;

        tempPath = PlayerSkillDataFolderDirection + "/" + "sbs_playerskilldata" + "/skill_cooldown_delay_setting.txt";

        String tempLine;
        File tempFile = new File(tempPath);

        if (tempFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(tempPath))) {
                while ((tempLine = reader.readLine()) != null) {
                    String[] keyValuePair = tempLine.split(Splitor, 2);

                    if (keyValuePair.length > 1) {
                        playerSkillID2CooldownDelay.put(keyValuePair[0], Float.valueOf(keyValuePair[1]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void SkillCooldownDelayPlayernameFileWriteTo() {
        try {
            HashMap<String, Float> playerSkillID2CooldownDelay = getCPlayerSkillID2CooldownDelay();

            String PlayerSkillDataFolderDirection = "config/"+ Constants.MOD_ID+"/";

            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ "sbs_playerskilldata";

            Files.createDirectories(Paths.get(tempPathStr));

            tempPathStr = tempPathStr +"/skill_cooldown_delay_setting.txt";

            FileWriter tempFileWriter = new FileWriter(tempPathStr);
            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);

            for (Map.Entry<String, Float> entry : playerSkillID2CooldownDelay.entrySet()) {
                if (entry.getValue() > 0) {
                    tempBufferedWriter.write(entry.getKey() + Splitor + entry.getValue());
                    tempBufferedWriter.newLine();
                }
            }

            tempBufferedWriter.flush();
            tempBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //keyboard hotkey save
    public static void ClientQuickslotPlayernameFileReadFrom() {
        HashMap<Integer, HashMap<String, Byte>> playerQuickslotKey = getCPlayerKey2SkillID();
        HashMap<String, Integer> playerSkillID2Key = getCPlayerSkillID2Key();

        String PlayerSkillDataFolderDirection = "config/"+ Constants.MOD_ID+"/";
        String tempPath;

        tempPath = PlayerSkillDataFolderDirection + "/" + "sbs_playerskilldata" + "/keyboard_setting_quickslot.txt";

        String tempLine;
        File tempFile = new File(tempPath);

        int tempKeyCode;
        byte tempByte = 0;
        if (tempFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(tempPath))) {
                while ((tempLine = reader.readLine()) != null) {
                    String[] keyValuePair = tempLine.split(Splitor, 2);

                    if (keyValuePair.length > 1) {
                        tempKeyCode = Integer.valueOf(keyValuePair[0]);
                        if (!playerQuickslotKey.containsKey(tempKeyCode)) {
                            playerQuickslotKey.put(tempKeyCode, new HashMap<>());
                        }
                        playerQuickslotKey.get(tempKeyCode).put(keyValuePair[1], tempByte);
                        playerSkillID2Key.put(keyValuePair[1], tempKeyCode);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ClientQuickslotPlayernameFileWriteTo() {
        try {
            HashMap<Integer, HashMap<String, Byte>> playerQuickslotKey = getCPlayerKey2SkillID();

            String PlayerSkillDataFolderDirection = "config/"+ Constants.MOD_ID+"/";

            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ "sbs_playerskilldata";

            Files.createDirectories(Paths.get(tempPathStr));

            tempPathStr = tempPathStr +"/keyboard_setting_quickslot.txt";

            FileWriter tempFileWriter = new FileWriter(tempPathStr);
            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);

            for (Map.Entry<Integer, HashMap<String, Byte>> entry : playerQuickslotKey.entrySet()) {
                for (String tempSkillID: entry.getValue().keySet()) {
                    tempBufferedWriter.write(entry.getKey() + Splitor + tempSkillID);
                    tempBufferedWriter.newLine();
                }
            }

            tempBufferedWriter.flush();
            tempBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ClientKeycomboPlayernameFileReadFrom() {
        HashMap<ArrayList<Integer>, HashMap<String, Byte>> playerKeycomboList = getCPlayerKeyCombo2SkillID();
        HashMap<String, ArrayList<Integer>> playerSkillID2KeycomboList = getCPlayerSkillID2KeyCombo();

        String PlayerSkillDataFolderDirection = "config/"+ Constants.MOD_ID+"/";
        String tempPath;
        byte tempByte = 0;

        tempPath = PlayerSkillDataFolderDirection + "/" + "sbs_playerskilldata" + "/keyboard_setting_keycombolist.txt";

        String tempLine;
        File tempFile = new File(tempPath);
        if (tempFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(tempPath))) {
                while ((tempLine = reader.readLine()) != null) {
                    String[] keyValuePair = tempLine.split(Splitor);

                    if (keyValuePair.length > 1) {
                        ArrayList<Integer> tempArrList = new ArrayList<Integer>();

                        for (int k = 0; k < keyValuePair.length-1; k++) {
                            tempArrList.add(Integer.valueOf(keyValuePair[k]));
                        }

                        if (!playerKeycomboList.containsKey(tempArrList)) {
                            playerKeycomboList.put(tempArrList, new HashMap<>());
                        }
                        playerKeycomboList.get(tempArrList).put(keyValuePair[keyValuePair.length-1], tempByte);
                        playerSkillID2KeycomboList.put(keyValuePair[keyValuePair.length-1], tempArrList);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ClientKeycomboPlayernameFileWriteTo() {
        try {
            HashMap<ArrayList<Integer>, HashMap<String, Byte>> playerKeycomboList = getCPlayerKeyCombo2SkillID();

            String PlayerSkillDataFolderDirection = "config/"+ Constants.MOD_ID+"/";

            String tempPathStr = PlayerSkillDataFolderDirection +"/"+ "sbs_playerskilldata";

            Files.createDirectories(Paths.get(tempPathStr));

            tempPathStr = tempPathStr +"/keyboard_setting_keycombolist.txt";

            FileWriter tempFileWriter = new FileWriter(tempPathStr);
            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);

            StringBuilder tempKeyComboString;

            for (ArrayList<Integer> tempArrList: playerKeycomboList.keySet()) {
                tempKeyComboString = new StringBuilder();
                for (int keyCode: tempArrList) {
//                    tempBufferedWriter.write(keyCode + Splitor);
                    tempKeyComboString.append(keyCode).append(Splitor);
                }

                if (playerKeycomboList.containsKey(tempArrList)) {
                    HashMap<String, Byte> tempString2KeycomboList = playerKeycomboList.get(tempArrList);
                    for (String SkillID: tempString2KeycomboList.keySet()) {
                        tempBufferedWriter.write(tempKeyComboString.toString() + SkillID);
                        tempBufferedWriter.newLine();
                    }
                }
            }

            tempBufferedWriter.flush();
            tempBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
