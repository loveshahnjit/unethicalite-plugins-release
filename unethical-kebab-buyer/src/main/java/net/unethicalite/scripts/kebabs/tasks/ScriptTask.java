package net.unethicalite.scripts.kebabs.tasks;

public interface ScriptTask
{
	/**
	 * The check to validate the execution of the task.
	 *
	 * @return whether the task should be executed or not.
	 */
	int execute();

	/**
	 * @return true if the task blocks subsequent tasks.
	 */
	default boolean blocking()
	{
		return true;
	}
}
