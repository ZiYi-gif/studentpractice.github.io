package net.puge.student.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.puge.student.entity.PugeStudent;
import net.puge.student.entity.vo.StudentInfoQuery;
import net.puge.student.service.PugeStudentService;
import net.puge.student.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-11-17
 */
@RestController
@RequestMapping("/student/puge-student")
public class PugeStudentController {
    @Autowired
    private PugeStudentService pugeStudentService;

    /**
     * 查找所有学生信息
     * @return
     */
    @GetMapping
    public R selectAll() {
        List<PugeStudent> list = pugeStudentService.list(null);
        return R.ok().data("items",list);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("select/{id}")
    public R selectStudentInfo(@PathVariable("id") String id){
        PugeStudent selectId = pugeStudentService.select(id);
        return R.ok().data("seletId",selectId);
    }



    /**
     * 增加学生信息
     * @param pugeStudent
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public R saveStudentInfo(@RequestBody PugeStudent pugeStudent) throws Exception {
        pugeStudentService.saveStudent(pugeStudent);
        return R.ok();
    }

    /**
     * 修改学生信息
     * @param pugeStudent
     * @return
     */
    @PatchMapping("update")
    public R updateStudentInfo(@RequestBody PugeStudent pugeStudent) {
        pugeStudentService.updateById(pugeStudent);
        return R.ok();
    }

    /**
     * 逻辑删除
     * @param pugeStudent
     * @return
     */
    @DeleteMapping({"{id}"})
    public String deleteSubscribeSeat(@RequestBody PugeStudent pugeStudent){
        boolean removeById = pugeStudentService.removeById(pugeStudent);
        if(removeById) {
            return "code=20000";
        } else {
            return "code=20001";
        }
    }

    /**
     * 分页查询
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("pageStudent/{current}/{limit}")
    public R pageGetStudentInfo(@PathVariable long current,@PathVariable long limit){
        Page<PugeStudent> pageStudent = new Page<>(current,limit);

        pugeStudentService.page(pageStudent,null);

        long total = pageStudent.getTotal();
        List<PugeStudent> records = pageStudent.getRecords();

        return R.ok().data("total",total).data("rows",records);

    }

    /**
     * 条件查询
     * @return
     */
    @PostMapping("search")
    public R SearchStudentInfo(@RequestBody StudentInfoQuery req){
        return pugeStudentService.search();
    }


}

