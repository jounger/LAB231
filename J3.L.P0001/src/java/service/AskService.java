/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Ask;

/**
 *
 * @author nguyenvanan
 */
public interface AskService {

    List<Ask> getByQuiz(int page, int limit, int quiz_id);

    void createAskInQuiz(Ask ask, int quiz_id);
}
