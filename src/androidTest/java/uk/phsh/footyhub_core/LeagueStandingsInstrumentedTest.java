package uk.phsh.footyhub_core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import okhttp3.Headers;
import uk.phsh.footyhub_core.enums.LeagueEnum;
import uk.phsh.footyhub_core.interfaces.I_TaskCallback;
import uk.phsh.footyhub_core.models.LeagueStanding;
import uk.phsh.footyhub_core.models.RestResponse;
import uk.phsh.footyhub_core.tasks.LeagueStandingsTask;

public class LeagueStandingsInstrumentedTest {
    LeagueStandingsTask lst = new LeagueStandingsTask(LeagueEnum.PREMIER_LEAGUE, new I_TaskCallback<>() {
        @Override
        public void onSuccess(LeagueStanding leagueStanding) { }

        @Override
        public void onError(String message) { }
    });

    @Test
    public void getUrl() {
        assertEquals("https://api.football-data.org/v4/competitions/PL/standings", lst.getUrl());
    }

    @Test
    public void getHeaders() {
        Headers headers = lst.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.names().contains("X-Auth-Token"));
    }

    @Test
    public void call() {
        try {
            RestResponse response = lst.call();
            assertNotNull(response);
            assertEquals(200, response.getResponseCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
