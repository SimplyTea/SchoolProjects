package test;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Comparator;
import java.util.function.Consumer;

public class EventBus {
    private final Map<Class<?>, List<PriorityListener<?>>> listeners = new HashMap<>();

    public <T extends CancellableEvent> void register(Class<T> eventType, Consumer<T> listener, EventPriority priority) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(new PriorityListener<>(listener, priority));
        listeners.get(eventType).sort(Comparator.comparingInt(pl -> pl.getPriority()).reversed());
    }

    public <T extends CancellableEvent> void unregister(Class<T> eventType, Consumer<T> listener) {
        List<PriorityListener<?>> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            eventListeners.removeIf(pl -> pl.getListener().equals(listener));
        }
    }

    public <T extends CancellableEvent> void post(T event) {
        List<PriorityListener<?>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (PriorityListener<?> priorityListener : eventListeners) {
                @SuppressWarnings("unchecked")
                Consumer<T> consumer = (Consumer<T>) priorityListener.getListener();
                consumer.accept(event);
                if (event.isCancelled()) {
                    break; // Stop processing if event is cancelled
                }
            }
        }
    }

    private static class PriorityListener<T> {
        private final Consumer<T> listener;
        private final EventPriority priority;

        public PriorityListener(Consumer<T> listener, EventPriority priority) {
            this.listener = listener;
            this.priority = priority;
        }

        public Consumer<T> getListener() {
            return listener;
        }

        public int getPriority() {
            return priority.getLevel();
        }
    }
}

enum EventPriority {
    HIGH(10),
    MEDIUM(5),
    LOW(1);

    private final int level;

    EventPriority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

abstract class CancellableEvent {
    private boolean cancelled = false;

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }
}

// Example usage:
class ExampleUsage {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        
        eventBus.register(TestEvent.class, event -> {
            System.out.println("High Priority Listener: " + event.getMessage());
            event.cancel(); // Cancel the event
        }, EventPriority.HIGH);

        eventBus.register(TestEvent.class, event -> {
            System.out.println("Low Priority Listener: " + event.getMessage());
        }, EventPriority.LOW);
        
        eventBus.post(new TestEvent("Hello EventBus!"));
    }
}

class TestEvent extends CancellableEvent {
    private final String message;

    public TestEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
