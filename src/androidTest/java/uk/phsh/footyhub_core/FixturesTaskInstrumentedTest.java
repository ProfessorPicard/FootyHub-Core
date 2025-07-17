package uk.phsh.footyhub_core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import okhttp3.Headers;
import uk.phsh.footyhub_core.enums.LeagueEnum;
import uk.phsh.footyhub_core.interfaces.I_TaskCallback;
import uk.phsh.footyhub_core.models.CompetitionFixtures;
import uk.phsh.footyhub_core.models.RestResponse;
import uk.phsh.footyhub_core.tasks.FixturesTask;

public class FixturesTaskInstrumentedTest {
    FixturesTask ft = new FixturesTask(new I_TaskCallback<>() {
        @Override
        public void onSuccess(CompetitionFixtures fixtures) { }

        @Override
        public void onError(String message) { }
    }, LeagueEnum.PREMIER_LEAGUE, 1);

    @Test
    public void getUrl() {
        assertEquals("https://api.football-data.org/v4/competitions/PL/matches?matchday=1", ft.getUrl());
    }

    @Test
    public void getHeaders() {
        Headers headers = ft.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.names().contains("X-Auth-Token"));
    }

    @Test
    public void call() {
        try {
            RestResponse response = ft.call();
            assertNotNull(response);
            assertEquals(200, response.getResponseCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
