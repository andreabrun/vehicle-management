package com.andreabrun.vehiclemanagement.events;
import java.util.ArrayList;
import java.util.List;

import com.andreabrun.vehiclemanagement.listeners.PageChangedListener;

public class PageChangedEventPublisher {
    private final List<PageChangedListener> listeners = new ArrayList<>();

    public void addListener(PageChangedListener listener) {
        listeners.add(listener);
    }

    public void removeListener(PageChangedListener listener) {
        listeners.remove(listener);
    }

    public void fireEvent(PageChangedEvent event) {
        for (PageChangedListener listener : listeners) {
            listener.onPageChanged(event);
        }
    }
}