package com.test.seminar.dao;

import com.test.seminar.entity.Team;
import com.test.seminar.exception.RepetitiveRecordException;
import com.test.seminar.exception.TeamNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author cxh
 * @date 2018/11/29
 */
public interface TeamDao {
    /**
     * 通过ID获取队伍信息
     * @param teamId
     * @return
     */
    Team getTeamByTeamId(BigInteger teamId)throws TeamNotFoundException;

    /**
     * 创建新的队伍信息
     * @param team
     * @return
     */
    void insertTeam(Team team)throws RepetitiveRecordException;

    /**
     * 更改队伍信息
     * @param team
     * @return
     */
    void updateTeamByTeamId(Team team)throws TeamNotFoundException;

    /**
     *
     * @param teamId
     * @return
     */
    void deleteTeamByTeamId(BigInteger teamId) throws TeamNotFoundException;

    /**
     * 以学生ID和课程ID获取学生本课程的组队信息
     * @param studentId
     * @param courseId
     * @return
     */
    Team getTeamByStudentIdAndCourseId(BigInteger studentId,BigInteger courseId) throws TeamNotFoundException;

    /**
     * 查看某课程的所有队伍
     * @param courseId
     * @return
     */
    List<Team> getTeamByCourseId(BigInteger courseId);

    /**
     * 查看某讨论课的所有队伍
     * @param seminarControlId
     * @return
     */
    List<Team> getTeamBySeminarControlId(BigInteger seminarControlId);

    /**
     * 更新某班级下某学生的队伍
     * @param courseClassId
     * @param studentId
     * @param teamId
     * @return
     */
    void updateCourseClassStudentTeamId(BigInteger courseClassId,BigInteger studentId,BigInteger teamId);
}
