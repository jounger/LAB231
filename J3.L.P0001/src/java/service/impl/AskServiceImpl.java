/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.impl.AskDAOImpl;
import java.util.ArrayList;
import java.util.List;
import model.Ask;
import service.AskService;

/**
 *
 * @author nguyenvanan
 */
public class AskServiceImpl implements AskService {

    private final AskDAOImpl askDAOImpl = new AskDAOImpl();

    @Override
    public List<Ask> getByQuiz(int page, int limit, int quiz_id) {
        List<Ask> asks = new ArrayList<>();
        asks = askDAOImpl.findByQuiz(page, limit, quiz_id);
        return asks;
    }

    @Override
    public void createAskInQuiz(Ask ask, int quiz_id) {
        askDAOImpl.saveInQuiz(ask, quiz_id);
    }
}
