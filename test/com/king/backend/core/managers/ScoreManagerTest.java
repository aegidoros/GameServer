package com.king.backend.core.managers;

import com.king.backend.core.models.HighScore;
import com.king.backend.core.models.UserScore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScoreManagerTest {

    ScoreManager scoreManager;

    @Before
    public void setUp() throws Exception {
        scoreManager = new ScoreManager();
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testSaveScore() throws Exception {
        int levelId = 1;
        int userId = 42;
        int score = 500;
        UserScore userScore = new UserScore(userId, score);
        scoreManager.saveScore(userScore, levelId);
        HighScore highScore = scoreManager.getHighScore(levelId);
        Assert.assertEquals("HighScore Invalid", "42=500", highScore.toString());
    }

    @Test
    public void testLevelIdWithoutScores() throws Exception {
        HighScore highScore = scoreManager.getHighScore(555);
        Assert.assertEquals("HighScore Invalid", "", highScore.toString());
    }


    @Test
    public void testUniqueScorePerUser() throws Exception {
        int levelId = 2;
        int userId = 42;
        int score = 500;
        HighScore highScore;
        UserScore userScore1 = new UserScore(userId, score);
        scoreManager.saveScore(userScore1, levelId);
        highScore = scoreManager.getHighScore(levelId);
        System.out.println("levelScore = " + highScore);
        Assert.assertEquals("HighScore Invalid", "42=500", highScore.toString());
        UserScore userScore2 = new UserScore(userId, score+50);
        scoreManager.saveScore(userScore2, levelId);
        highScore = scoreManager.getHighScore(levelId);
        System.out.println("levelScore = " + highScore);
        Assert.assertEquals("HighScore Invalid", "42=550", highScore.toString());
        UserScore userScore3 = new UserScore(userId, score-50);
        scoreManager.saveScore(userScore3, levelId);
        highScore = scoreManager.getHighScore(levelId);
        System.out.println("levelScore = " + highScore);
        Assert.assertEquals("HighScore Invalid", "42=550", highScore.toString());
        UserScore userScore4 = new UserScore(userId, score+100);
        scoreManager.saveScore(userScore4, levelId);
        highScore = scoreManager.getHighScore(levelId);
        System.out.println("levelScore = " + highScore);
        Assert.assertEquals("HighScore Invalid", "42=600", highScore.toString());
    }



    @Test
    public void testSeveralScore() throws Exception {
        int levelId = 3;
        HighScore highScore;
        UserScore userScore1 = new UserScore(42, 500);
        scoreManager.saveScore(userScore1, levelId);
        highScore = scoreManager.getHighScore(levelId);
        System.out.println("levelScore = " + highScore);
        Assert.assertEquals("HighScore Invalid", "42=500", highScore.toString());
        UserScore userScore2 = new UserScore(72, 450);
        scoreManager.saveScore(userScore2, levelId);
        highScore = scoreManager.getHighScore(levelId);
        System.out.println("levelScore = " + highScore);
        Assert.assertEquals("HighScore Invalid", "42=500,72=450", highScore.toString());
        UserScore userScore3 = new UserScore(42, 600);
        scoreManager.saveScore(userScore3, levelId);
        highScore = scoreManager.getHighScore(levelId);
        System.out.println("levelScore = " + highScore);
        Assert.assertEquals("HighScore Invalid", "42=600,72=450", highScore.toString());
        UserScore userScore4 = new UserScore(72, 700);
        scoreManager.saveScore(userScore4, levelId);
        highScore = scoreManager.getHighScore(levelId);
        System.out.println("levelScore = " + highScore);
        Assert.assertEquals("HighScore Invalid", "72=700,42=600", highScore.toString());
        UserScore userScore5 = new UserScore(10, 650);
        scoreManager.saveScore(userScore5, levelId);
        highScore = scoreManager.getHighScore(levelId);
        System.out.println("levelScore = " + highScore);
        Assert.assertEquals("HighScore Invalid", "72=700,10=650,42=600", highScore.toString());
    }


    @Test
    public void testMaxLimitScore() throws Exception {
        int levelId = 4;
        for(int i =1 ; i < 17; i++){
            UserScore userScore1 = new UserScore(i, i);
            scoreManager.saveScore(userScore1, levelId);
        }
        int userId = 42;
        int score = 500;
        UserScore userScore = new UserScore(userId, score);
        scoreManager.saveScore(userScore, levelId);
        HighScore highScore = scoreManager.getHighScore(levelId);
        Assert.assertEquals("HighScore Invalid", "42=500", highScore.toString());
    }



    @Test
    public void testGetLevelScore() throws Exception {

    }
}