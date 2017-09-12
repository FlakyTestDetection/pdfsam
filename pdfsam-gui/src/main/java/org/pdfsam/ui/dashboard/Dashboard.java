/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 02/mag/2014
 * Copyright 2017 by Sober Lemur S.a.s. (info@pdfsam.org).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pdfsam.ui.dashboard;

import static org.sejda.eventstudio.StaticStudio.eventStudio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.pdfsam.ui.event.SetActiveDashboardItemRequest;
import org.pdfsam.ui.event.SetTitleEvent;
import org.pdfsam.ui.quickbar.QuickbarPane;
import org.pdfsam.ui.support.Style;
import org.sejda.eventstudio.annotation.EventListener;

import javafx.animation.FadeTransition;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Panel showing the app dashboard
 * 
 * @author Andrea Vacondio
 */
public class Dashboard extends BorderPane {

    private Map<String, DashboardItemPane> items = new HashMap<>();
    private StackPane center = new StackPane();
    private FadeTransition fade = new FadeTransition(new Duration(300), center);

    @Inject
    public Dashboard(List<DashboardItem> itemsList, QuickbarDashboardButtonsPane dashboardButtons) {
        getStyleClass().addAll(Style.CONTAINER.css());
        setId("pdfsam-dashboard");
        itemsList.stream().forEach(i -> items.put(i.id(), new DashboardItemPane(i)));
        fade.setFromValue(0);
        fade.setToValue(1);
        setCenter(center);
        setLeft(new QuickbarPane(dashboardButtons));
        eventStudio().addAnnotatedListeners(this);
    }

    @EventListener
    public void onSetActiveDashboardItem(SetActiveDashboardItemRequest request) {
        DashboardItemPane requested = items.get(request.getActiveItemId());
        if (requested != null) {
            center.getChildren().setAll(requested);
            fade.play();
            eventStudio().broadcast(new SetTitleEvent(requested.getItem().name()));
        }
    }

}
