package cn.edu.hpu.autoweb.service.system;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.entity.FilePath;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.UuidUtil;
import cn.edu.hpu.autoweb.util.mes.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Aries on 2017/4/25.
 */
@Transactional
@Service
public class FilePathService extends BaseService{

    @Autowired
    private DaoSupport daoSupport;
    @Autowired
    private CommonService commonService;
    private final String table_filepath = "filepath";
    private final String idField = "fileId";
    public Map saveFilePath(String fileName,String tableName,String tableId) throws Exception {
        FilePath filePath = new FilePath();
        filePath.setFileId(UuidUtil.get32UUID());
        filePath.setTableName(tableName);
        filePath.setTableId(tableId);
        filePath.setFilePath(fileName);
        return commonService.insert(FilePath.class,filePath,table_filepath);
    }

    public Map findFilesByTableIdAndTableName(PageData pd) throws Exception {
        return queryDaoDataT("FilePathMapper","queryFilesByTableNameAndTableId",pd);
    }

    public void deleteFilePathByTableId(String tableName,String tableId) throws Exception {
        PageData pd =new PageData();
        pd.put("tablename",tableName);
        pd.put("tableid",tableId);
        daoSupport.delete("FilePathMapper.deleteFilePathByTableId",pd);
    }

    public Map deleteFilePath(String id) throws Exception {
        return commonService.delete(id,table_filepath,"fileId");
    }

    public Map findFileById(String id) throws Exception {
        return StringUtils.toLowerMap(commonService.findById(id,idField,table_filepath));
    }
}
