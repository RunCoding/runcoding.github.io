package com.runcoding.service.translation;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

@Slf4j
public class AlphaCoding {

    /**
     *
     * 一、解析本地文件获取单词与词频
     * egrep -o -i "\b[[:alpha:]]+\b" -r flink-java/** \
     *     |awk '{++count[$0]} END{for (word in count){ printf("%-20s,%d\n",word,count[word]);}}' \
     *     |sort -n -r -k2,2  -t ',' |head -200000 > alpha-c.csv
     * 二、 爬取单词词意(美式男音)
     * curl http://dict.cn/forward |grep naudio |  awk -v FS="(naudio=\"|\" title)" '{print "http://audio.dict.cn/" $2}' | sed -n '4p' | xargs wget -O forward.mp3
     * 三、 爬取谷歌单词
     *     http://localhost:3333/main/dist/translate/translate.html
     */
    @Test
    public void alpha() throws Exception {
        File dir = new File("/Users/xukai/Downloads/alpha");
        Map<String,Integer> alphaMap = Maps.newHashMapWithExpectedSize(100000);
        List<File> fileList = (List<File>)FileUtils.listFiles(dir,null,true);
        fileList.stream().forEach(file -> {
            if(file.isFile() && StringUtils.endsWith(file.getName(),"csv")){
                log.info("处理文件:{}",file.getAbsolutePath());
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    //InputStream stencilsetStream = AlphaCoding.class.getClassLoader().getResourceAsStream("translation/alpha-c.csv");
                    List<String> alphaCsv = IOUtils.readLines(inputStream, "utf-8");
                    alphaCsv.forEach(alpha->{
                        String[] alphaArr  = StringUtils.split(alpha, ",");
                        String       words  = StringUtils.trim(alphaArr[0]);
                        Integer      count = Integer.valueOf(alphaArr[1]);
                        /**处理驼峰*/
                        String[] wordCamelCase = StringUtils.splitByCharacterTypeCamelCase(words);
                        if(wordCamelCase.length > 1){
                            count = 1;
                        }
                        for (int i = 0; i < wordCamelCase.length; i++) {
                            String word = StringUtils.lowerCase(wordCamelCase[i]);
                            if(!StringUtils.isAlphaSpace(word) || word.length() <= 1){
                                return;
                            }
                            Integer currCount = alphaMap.get(word);
                            currCount =  currCount == null ? count : currCount + count;
                            alphaMap.put(word,currCount);
                        }
                    });
                } catch (Exception e) {
                   log.error("读取文件失败",e);
                }
            }
        });
        List<String> alphaCsv = Lists.newArrayList();
        alphaCsv.add("word,count");
        alphaMap.forEach((word,count)->{
            alphaCsv.add(word+","+count);
        });
        /**保存结果*/
        FileOutputStream outputStream = new FileOutputStream("/Users/xukai/Downloads/alpha.csv");
        IOUtils.writeLines(alphaCsv, null, outputStream);
        log.info("处理完成");
    }

}
