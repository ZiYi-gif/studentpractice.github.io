package net.puge.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.puge.student.entity.PugeStudent;
import net.puge.student.entity.vo.StudentInfoQuery;
import net.puge.student.mapper.PugeStudentMapper;
import net.puge.student.service.PugeStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.puge.student.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-01-05
 */
@Service
public class PugeStudentServiceImpl extends ServiceImpl<PugeStudentMapper, PugeStudent> implements PugeStudentService {

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public PugeStudent select(String id) {
        QueryWrapper<PugeStudent> wrapperId = new QueryWrapper<>();
        wrapperId.eq("id", id);
        PugeStudent selectOne = baseMapper.selectOne(wrapperId);
        return selectOne;
    }

    /**
     * 增加学生信息
     *
     * @param pugeStudent
     * @throws Exception
     */
    @Override
    public void saveStudent(PugeStudent pugeStudent) throws Exception {
        QueryWrapper<PugeStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("name", pugeStudent.getName());
        List<PugeStudent> students = (List<PugeStudent>) baseMapper.selectById(wrapper);

        if (students.isEmpty()) {
            baseMapper.insert(pugeStudent);
        } else {
            throw new Exception("姓名重复，请重新输入");
        }
    }


    /**
     * 条件查询
     */
    @Override
    public R search(StudentInfoQuery req) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty(req.getName())) {
            queryWrapper.like("name", req.getName());
        }
        if(StringUtils.isNotEmpty(req.getSex())){
            queryWrapper.eq("sex",req.getSex());
        }
        queryWrapper.orderByAsc("name");
        baseMapper.selectPage()
    }


}
