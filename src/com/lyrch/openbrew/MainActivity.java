package com.lyrch.openbrew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button newRecipeButton;
	private Button recipeBookButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.open_brew_main_layout);
		
		newRecipeButton = (Button) findViewById(R.id.newRecipeButton);
		newRecipeButton.setOnClickListener(newRecipeListener);
		recipeBookButton = (Button) findViewById(R.id.recipeBookButton);
		recipeBookButton.setOnClickListener(recipeBookListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.open_brew_main, menu);
		return true;
	}

	private OnClickListener newRecipeListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent newRecipeIntent = new Intent(MainActivity.this, RecipeActivity.class);
			MainActivity.this.startActivity(newRecipeIntent);
		}
	};
	
	private OnClickListener recipeBookListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent recipeBookIntent = new Intent(MainActivity.this, RecipeBookActivity.class);
			MainActivity.this.startActivity(recipeBookIntent);
		}
	};
}
