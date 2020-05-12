package net.rusnet.taskmanager.edit.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import net.rusnet.taskmanager.R
import net.rusnet.taskmanager_old.commons.domain.model.Task

class EditActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_TASK = "EXTRA_TASK"

        fun getIntentForNewTask(activity: Activity): Intent {
            return Intent(activity, EditActivity::class.java)
        }

        fun getIntentForExistingTask(activity: Activity, task: Task): Intent {
            return Intent(activity, EditActivity::class.java).apply {
                putExtra(EXTRA_TASK, task)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        initActionBar()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        showExitWithoutSavingDialog { super.onBackPressed() }
    }

    private fun showExitWithoutSavingDialog(onPositiveButtonPressed: () -> Unit) {
        // todo: show dialog only if any changes where made
        AlertDialog.Builder(this)
            .setTitle(R.string.exit_without_saving_warning)
            .setPositiveButton(R.string.yes) { _, _ ->
                onPositiveButtonPressed()
            }
            .setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun initActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title =
                if (intent.hasExtra(EXTRA_TASK)) getString(R.string.title_existing_task) else getString(
                    R.string.title_new_task
                )
        }
    }

}
