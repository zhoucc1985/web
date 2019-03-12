package com.cloud.service.fileService;

import com.cloud.entity.datacollect.CollectTemplate;
import com.cloud.mapper.master.datacollect.CollectTemplateMapper;
import com.cloud.mapper.master.file.FileMapper;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileService {
    private static Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private CollectTemplateMapper collectTemplateMapper;
    @Autowired
    private FileMapper fileMapper;

    /**
     * 模板下载目录地址
     */
    @Value("${filepath.downloadbashpath}")
    private String serverPath;

    public void getFileDetails(String path, String sub, Map map, List list) {
        if(StringUtils.isEmpty(path)){
            path=serverPath;
        }
        getFileDetailsRecursion(serverPath,  sub,  map,  list);
    }

    /**
     * 递归补录数据到collect_template
     * @param path
     * @param sub
     * @param map
     * @param list
     */
    private void getFileDetailsRecursion(String path, String sub, Map map, List list) {
        File file = new File(path);//自己测试

        if (file.exists()) {
            File[] files = file.listFiles();//路径下所有文件或文件夹的集合
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {//判断是否是文件夹
                        getFileDetailsRecursion(file2.getAbsolutePath(), sub, map, list);
                    } else {
//                        System.out.println(file2.getPath());
                        CollectTemplate collectTemplate = new CollectTemplate();
                        //获取文件名--匹配表英文名
                        //系统名称
                        Integer parentId = 0;
                        String separator = File.separator;//分隔符


                        map = getFileMap(map, file2, separator);
                        if ((String) map.get("1") != null && !"".equals((String) map.get("1"))) {
                            String sysName = map.get("1") + "";
                            Map<String, Object> collecEnum = fileMapper.selectSysNameCode(sysName);
                            if (collecEnum != null) {
                                collectTemplate.setSystemName(Integer.parseInt(collecEnum.get("value") + ""));
                                parentId = (Integer) collecEnum.get("id");
                            }
                        }

                        String subPath = getSubstringPath(separator, file2.getPath());
                        subPath = subPath.replaceAll("\\\\", "/");
                        collectTemplate.setTemplatePath(subPath);//模板路径

                        String fileName = file2.getName().substring(0, file2.getName().lastIndexOf("."));
                        String tableName = fileMapper.selectByFileName(fileName);//文件中文名有重复，需要前缀
                        if (tableName == null || "".equals(tableName)) {//没有匹配到表名
                            ImmutableMap<String, String> noFindTableName = ImmutableMap.of("url", file2.getPath(), "name", file2.getName());
                            list.add(noFindTableName);
                        }
                        collectTemplate.setTempTableName("tmp_" + tableName);//模板名
                        collectTemplate.setTemplateName(fileName);
                        collectTemplate.setRealTableName(tableName);

//                        if ((String) map.get("2") != null && !"".equals((String) map.get("2"))) {
//                            String firstName = map.get("2") + "";
//                            String firstCode = fileMapper.selectFirstCode(firstName, parentId);
//                            if (firstCode != null) {
//                                collectTemplate.setFirstTypeCode(Integer.parseInt(firstCode));
//                            }
//                        }

                        if ((String) map.get("2") != null && !"".equals((String) map.get("2"))) {
                            String secondName = map.get("2") + "";
                            String secondCode = fileMapper.selectSecondCode(secondName, parentId);
                            if (secondCode != null) {
                                collectTemplate.setSecondTypeCode(Integer.parseInt(secondCode));
                            }
                        }
                        collectTemplateMapper.insert(collectTemplate);
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("没有匹配到表名的--->" + list);
    }

    /**
     * 截取文件templatePath
     *
     * @param s
     * @param path
     * @return
     */
    private String getSubstringPath(String s, String path) {
        int count = 0;
        String result = "";
        for (int i = 0; i < path.length() - 1; i++) {
            result = path.substring(i);
            if (result.startsWith(s)) {
                ++count;
            }
            if (count == 4) {
                return result;
            }
        }
        return result;
    }

    private Map getFileMap(Map map, File file2, String separator) {
        String absolutePath = file2.getAbsolutePath();
        String[] split1 = absolutePath.replace("\\","/").split("/");//文件路径
        String[] split2 = this.serverPath.replace("\\","/").split("/");//目标路径
        String filePath = "";
        String serverSplitPath = "";
        for (String s : split2) {
            serverSplitPath = serverSplitPath + s;
        }
        for (int i = 0; i < split1.length; i++) {
            filePath = filePath + split1[i];
            System.out.println(filePath);
            if (serverSplitPath.equals(filePath)) {
                if (i + 1 < split1.length) {
                    map.put("1", split1[i + 1]);

                    System.out.println(split1[i + 1]);
                }

                if (i + 2 < split1.length) {
                    map.put("2", split1[i + 2]);

                    System.out.println(split1[i + 2]);
                }

//                if (i + 3 < split1.length) {
//                    map.put("3", split1[i + 3]);
//
//                    System.out.println(split1[i + 1]);
//                }

            }
        }
        return map;
    }

}
