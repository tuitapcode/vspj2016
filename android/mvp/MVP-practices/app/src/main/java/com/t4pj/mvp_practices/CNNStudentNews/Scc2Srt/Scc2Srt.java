package com.t4pj.mvp_practices.CNNStudentNews.Scc2Srt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Akechi
 * Convert SCC subtitle to SRT subtitle
 * Done: 2016.07.02 05:21:40.00 AM
 *
 * SCC subtitle & SCC disassembly
 *
 * Scenarist_SCC V1.0 format
 *
 * 00:00:00:01	9425 9425 94AD 94AD 9170 9170 3E3E 205B 20CD D5D3 4943 205D
 * 00:00:11:25	9425 9425 94AD 94AD 9170 9170 D3EF 75F4 6820 C16D E5F2 E9E3 6120 E973 20F7 68E5 F2E5 20F7 E520 73F4 61F2 F420
 * [...]
 *
 * SCC_disassembly V1.2
 * CHANNEL 1
 *
 * 00:00:00:12	{RU2}{RU2}{CR}{CR}{0200}{0200}>> [ MUSIC ]
 * 00:00:12:16	{RU2}{RU2}{CR}{CR}{0200}{0200}South America is where we start
 * [...]
 *
 *
 * SRT subtitle format
 *
 * 1
 * 00:00:00,000 --> 00:00:30,000
 * First line
 * >> [ MUSIC ]
 *
 * 2
 * 00:00:30,000 --> 00:01:30,000
 * Second line
 * South America is where we start
 *
 * [...]
 */
public class Scc2Srt {

    public static void convert(String src_SCC_FileName, String des_SRT_FileName) throws IOException {
        //Array subtitle by line
        File SccFile = new File(src_SCC_FileName);
        InputStream is = new FileInputStream(SccFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String tmp_line = br.readLine();
        List<String> lines = new ArrayList<String>();

        while(tmp_line!=null) {
            if (!tmp_line.trim().isEmpty())
            {
                lines.add(tmp_line.trim());
            }
            tmp_line = br.readLine();

        }

        is.close();
        br.close();

//        List<String> lines = Files.readAllLines(Paths.get(src_SCC_FileName), StandardCharsets.US_ASCII);
        Model model = new Model();
        StringBuilder SRT = new StringBuilder();

        //Xóa bớt empty line
//        for (int i=0; i< lines.size(); i++) {
//            if (lines.get(i).isEmpty()) {
//                lines.remove(i);
//            }
//        }

        if (lines.get(0).contains("Scenarist_SCC V1.0")) {
            //Xử lý chuyển đổi subtitle
            for (int i=1; i< lines.size(); i++) {

                String line = lines.get(i);
                String lineNext = null;

                //Tách thời gian
                String[] data = line.split("\t");
                String[] dataNext = null;

                Time currentTime = new Time("h:m:s:f/fps", String.format("%s/29.97", data[0]));
                Time NextTime = null;

                if ( i < (lines.size()-1) ){
                    lineNext = lines.get(i+1);
                    dataNext = lineNext.split("\t");
                    NextTime = new Time("h:m:s:f/fps", String.format("%s/29.97", dataNext[0]));
                } else if (i == (lines.size()-1)) {
                    NextTime = currentTime;
                    NextTime.setMseconds(NextTime.getMseconds() + 180);
                }


                //Tách các nhóm 4hex
                data = data[1].split(" ");

                SRT.append(i + "\r\n");
                SRT.append(currentTime.getTime("hh:mm:ss,ms") + " --> " + NextTime.getTime("hh:mm:ss,ms") + "\r\n");

                for (int j = 0; j < data.length; j++) {
                    String word = data[j].toLowerCase();

                    // first check if word is a command
                    if (Model.COMMANDS.containsKey(word)){
                        //Do nothing
                    }
                    //second, check if word is a special character
                    else if (Model.SPECIAL_CHARS.containsKey(word)) {
                        //Do nothing
                    }
                    else if (Model.EXTENDED_CHARS.containsKey(word)) {
                        //Do nothing
                    }
                    //third, try to convert word into 2 characters
                    else {
                        String byte1 = word.substring(0, word.length()-2);
                        String byte2 = word.substring(word.length()-2);

                        if (Model.CHARACTERS.containsKey(byte1) && (Model.CHARACTERS.containsKey(byte2))) {
                            SRT.append(Model.CHARACTERS.get(byte1) + Model.CHARACTERS.get(byte2));
                        }
                    }
                }
                SRT.append("\r\n\r\n");

            }

            //Files.write(Paths.get(des_SRT_FileName), SRT.toString().getBytes());
            //Environment.getExternalStorageDirectory(),
            File SrtFile = new File(des_SRT_FileName);
            SrtFile.createNewFile();

            FileOutputStream SRT_FileOutputStream = new FileOutputStream(SrtFile);
            OutputStreamWriter SRT_OutputStreamWriter = new OutputStreamWriter(SRT_FileOutputStream);

            SRT_OutputStreamWriter.write(SRT.toString());

            SRT_OutputStreamWriter.close();
            SRT_FileOutputStream.close();

//            System.out.println(SRT.toString());
        }


    }
}
