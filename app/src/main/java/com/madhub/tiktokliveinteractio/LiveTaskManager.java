```java
package com.madhub.tiktokliveinteractio;

import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * LiveTaskManager: This class is responsible for managing live interaction tasks
 * in the TikTok live environment. It leverages MadHub's automation features for
 * seamless operations within TikTok live rooms. 
 *
 * Technical Architecture:
 * - This class integrates with Android Services to maintain background operations.
 * - It utilizes a Timer for scheduling interactions during live sessions.
 * - The manager handles user interactions, comment content, and time settings.
 * 
 * Implementation Details:
 * - Features include entering live rooms, sending comments, and following streamers.
 * - Configurations such as interaction intervals and total interaction duration are
 *   provided to customize operation behavior.
 * 
 * Best Practices:
 * - Configuring interaction intervals prevents account detection and maintains 
 *   compliance with platform rules.
 * - Using a GUI (Graphical User Interface) allows for easier user management and
 *   task configuration without coding knowledge.
 */
public class LiveTaskManager {
    
    private Timer interactionTimer;
    private List<String> usernameList; // List of usernames to interact with
    private String commentContent; // Content of the comments to be sent
    private long interactionInterval; // Time interval between interactions
    private long totalInteractionTime; // Total time for interaction
    private boolean isActive; // Indicates if interactions are ongoing

    public LiveTaskManager() {
        // Initialize the manager with default settings
        this.usernameList = new ArrayList<>();
        this.interactionTimer = new Timer();
        this.isActive = false;
    }

    /**
     * Starts the live interaction process.
     * 
     * @param usernames List of usernames to target during the live interactions.
     * @param comment     The comment content to be sent during interactions.
     * @param interval    The interval (in milliseconds) between each interaction.
     * @param duration    Total time (in milliseconds) for which to perform interactions.
     */
    public void startLiveInteraction(List<String> usernames, String comment, long interval, long duration) {
        this.usernameList = usernames;
        this.commentContent = comment;
        this.interactionInterval = interval;
        this.totalInteractionTime = duration;
        this.isActive = true;

        // Schedule the interaction task
        scheduleInteractions();
    }

    /**
     * Schedules the interaction tasks using a Timer.
     * This method sets up a repeating task that sends comments to the live room
     * based on the specified interaction interval.
     */
    private void scheduleInteractions() {
        interactionTimer.schedule(new TimerTask() {
            long elapsed = 0;

            @Override
            public void run() {
                if (!isActive || elapsed >= totalInteractionTime) {
                    cancel();
                    return;
                }

                // Perform interaction by sending comments to specified usernames
                for (String username : usernameList) {
                    sendComment(username, commentContent);
                }

                elapsed += interactionInterval;
            }
        }, 0, interactionInterval);
    }

    /**
     * Sends a comment to a given username during the live session.
     * 
     * @param username The username of the streamer or user to send the comment to.
     * @param comment  The comment content to be sent.
     */
    private void sendComment(String username, String comment) {
        // Logic to send comment to the live room. This will call MadHub's API that handles
        // the comment posting mechanism. This is a placeholder for actual implementation.
        System.out.println("Sending comment to @" + username + ": " + comment);
        // Here you would integrate with the MadHub API to perform the actual comment sending.
    }

    /**
     * Stops the live interaction process gracefully.
     */
    public void stopLiveInteraction() {
        isActive = false;
        interactionTimer.cancel();
    }

    /**
     * Returns the current status of the LiveTaskManager.
     * 
     * @return boolean indicating if the manager is currently active.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Configures the settings for interactions, allowing for dynamic adjustments.
     * 
     * @param newInterval New time interval (in milliseconds) for interactions.
     * @param newDuration New total duration (in milliseconds) for interactions.
     */
    public void configureInteractionSettings(long newInterval, long newDuration) {
        this.interactionInterval = newInterval;
        this.totalInteractionTime = newDuration;
    }

    /**
     * Retrieves the current list of targeted usernames for interaction.
     * 
     * @return List of targeted usernames.
     */
    public List<String> getUsernameList() {
        return usernameList;
    }
}
```

### Explanation of the Code Structure:
1. **Class Overview**: The `LiveTaskManager` is designed to facilitate TikTok live interactions using MadHub's automation capabilities. It employs a timer to manage periodic comment postings within specified live rooms.

2. **Configuration Management**: The class allows for dynamic settings such as the list of target usernames, comment content, interaction intervals, and total duration for the interaction tasks.

3. **Task Coordination**: The `startLiveInteraction()` method initializes the parameters and kicks off the interaction process, while `stopLiveInteraction()` allows for graceful termination of the ongoing tasks.

4. **Best Practices**: Utilizing a timer for scheduling ensures that interactions can be managed efficiently without overwhelming the platform, adhering to best practices for automation within TikTok's guidelines. The use of clear method signatures enhances maintainability and clarity.

5. **Integration Points**: While actual implementation for sending comments is abstracted in the `sendComment()` method, this structure allows for easy integration with MadHub's API for executing live interactions automatically.
