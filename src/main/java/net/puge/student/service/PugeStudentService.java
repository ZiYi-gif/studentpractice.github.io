package net.puge.student.service;

import net.puge.student.entity.PugeStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import net.puge.student.entity.vo.StudentInfoQuery;
import net.puge.student.utils.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2022-01-05
 */
public interface PugeStudentService extends IService<PugeStudent> {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    PugeStudent select(String id);

    /**
     * 增加学生信息
     * @param pugeStudent
     * @throws Exception
     */
    void saveStudent(PugeStudent pugeStudent) throws Exception;


    R search(StudentInfoQuery req);
}
