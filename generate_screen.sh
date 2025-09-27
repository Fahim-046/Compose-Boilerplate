#!/bin/bash

# ComposeBoilerplate Screen Generator
# This script generates a new screen with composable, viewmodel, and navigation setup

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

read -p "Enter the screen name (e.g., Profile, Dashboard): " SCREEN_NAME

# Validate input
if [ -z "$SCREEN_NAME" ]; then
    echo -e "${RED}Error: Screen name cannot be empty${NC}"
    exit 1
fi

# Convert screen name to different formats
SCREEN_NAME_LOWER=$(echo "$SCREEN_NAME" | tr '[:upper:]' '[:lower:]')
SCREEN_NAME_PASCAL="$SCREEN_NAME"
PACKAGE_NAME="com.fahimdev.composeboilerplate.presentation.${SCREEN_NAME_LOWER}"

# Define paths
BASE_PATH="app/src/main/java/com/fahimdev/composeboilerplate/presentation"
SCREEN_DIR="$BASE_PATH/$SCREEN_NAME_LOWER"
NAVIGATION_PATH="$BASE_PATH/naivgation"

echo -e "${BLUE}Creating screen: $SCREEN_NAME_PASCAL${NC}"
echo -e "${BLUE}Package: $PACKAGE_NAME${NC}"
echo ""

# Create directory structure
mkdir -p "$SCREEN_DIR"
mkdir -p "$SCREEN_DIR/components"
mkdir -p "$SCREEN_DIR/events"
mkdir -p "$SCREEN_DIR/states"

echo -e "${GREEN}✓ Created directory structure${NC}"

# Generate Screen composable
cat > "$SCREEN_DIR/${SCREEN_NAME_PASCAL}Screen.kt" << EOF
package $PACKAGE_NAME

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ${PACKAGE_NAME}.events.${SCREEN_NAME_PASCAL}Events
import com.fahimdev.composeboilerplate.presentation.base.BaseScreen
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme

@Composable
fun ${SCREEN_NAME_PASCAL}Screen(
    onNavigateBack: () -> Unit = {},
    viewModel: ${SCREEN_NAME_PASCAL}ViewModel = hiltViewModel()
) {
    val states = viewModel.states.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(key1 = states.message) {
        states.message?.value?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    ${SCREEN_NAME_PASCAL}ScreenSkeleton(
        onNavigateBack = onNavigateBack,
        onEvent = viewModel::onEvent,
        viewModel = viewModel
    )
}

@Composable
fun ${SCREEN_NAME_PASCAL}ScreenSkeleton(
    onNavigateBack: () -> Unit = {},
    onEvent: (${SCREEN_NAME_PASCAL}Events) -> Unit = {},
    viewModel: ${SCREEN_NAME_PASCAL}ViewModel? = null
) {
    BaseScreen(
        title = "${SCREEN_NAME_PASCAL}",
        showBackArrow = true,
        showTopBar = true
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${SCREEN_NAME_PASCAL} Screen",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Welcome to ${SCREEN_NAME_PASCAL}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun ${SCREEN_NAME_PASCAL}ScreenPreview() {
    ComposeBoilerplateTheme {
        ${SCREEN_NAME_PASCAL}ScreenSkeleton()
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun ${SCREEN_NAME_PASCAL}ScreenPreviewDark() {
    ComposeBoilerplateTheme(darkTheme = true) {
        ${SCREEN_NAME_PASCAL}ScreenSkeleton()
    }
}
EOF

echo -e "${GREEN}✓ Generated ${SCREEN_NAME_PASCAL}Screen.kt${NC}"

# Generate ViewModel
cat > "$SCREEN_DIR/${SCREEN_NAME_PASCAL}ViewModel.kt" << EOF
package $PACKAGE_NAME

import androidx.lifecycle.viewModelScope
import ${PACKAGE_NAME}.events.${SCREEN_NAME_PASCAL}Events
import ${PACKAGE_NAME}.states.${SCREEN_NAME_PASCAL}States
import com.fahimdev.composeboilerplate.presentation.base.BaseViewModel
import com.fahimdev.core.models.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ${SCREEN_NAME_PASCAL}ViewModel @Inject constructor(
    // Add your dependencies here
) : BaseViewModel() {
    val states = MutableStateFlow(${SCREEN_NAME_PASCAL}States())

    fun onEvent(event: ${SCREEN_NAME_PASCAL}Events) {
        when (event) {
            is ${SCREEN_NAME_PASCAL}Events.OnSampleAction -> {
                // Handle sample action
                states.value = states.value.copy(
                    message = Event("Sample action triggered")
                )
            }
        }
    }

    // Add your private methods here
}
EOF

echo -e "${GREEN}✓ Generated ${SCREEN_NAME_PASCAL}ViewModel.kt${NC}"

# Generate Events
cat > "$SCREEN_DIR/events/${SCREEN_NAME_PASCAL}Events.kt" << EOF
package ${PACKAGE_NAME}.events

sealed interface ${SCREEN_NAME_PASCAL}Events {
    data object OnSampleAction : ${SCREEN_NAME_PASCAL}Events
    // Add more events as needed
}
EOF

echo -e "${GREEN}✓ Generated ${SCREEN_NAME_PASCAL}Events.kt${NC}"

# Generate States
cat > "$SCREEN_DIR/states/${SCREEN_NAME_PASCAL}States.kt" << EOF
package ${PACKAGE_NAME}.states

import com.fahimdev.core.models.Event

data class ${SCREEN_NAME_PASCAL}States(
    val isLoading: Boolean = false,
    val message: Event<String>? = null,
    // Add more state properties as needed
)
EOF

echo -e "${GREEN}✓ Generated ${SCREEN_NAME_PASCAL}States.kt${NC}"

# Update Screen.kt navigation file
SCREEN_FILE="$NAVIGATION_PATH/Screen.kt"
if [ -f "$SCREEN_FILE" ]; then
    # Check if screen already exists
    if grep -q "data object $SCREEN_NAME_PASCAL : Screen" "$SCREEN_FILE"; then
        echo -e "${RED}✗ Screen $SCREEN_NAME_PASCAL already exists in Screen.kt${NC}"
    else
        # Add new screen before the closing brace of the sealed interface
        sed -i '' "/data object Dashboard : Screen/a\\
\\
    @Serializable\\
    data object $SCREEN_NAME_PASCAL : Screen" "$SCREEN_FILE"
        echo -e "${GREEN}✓ Added $SCREEN_NAME_PASCAL to Screen.kt${NC}"
    fi
else
    echo -e "${RED}✗ Screen.kt not found at $SCREEN_FILE${NC}"
fi

# Update NavigationRoot.kt
NAVIGATION_ROOT_FILE="$NAVIGATION_PATH/NavigationRoot.kt"
if [ -f "$NAVIGATION_ROOT_FILE" ]; then
    # Add import
    if ! grep -q "import ${PACKAGE_NAME}.${SCREEN_NAME_PASCAL}Screen" "$NAVIGATION_ROOT_FILE"; then
        sed -i '' "/import com.fahimdev.composeboilerplate.presentation.moviehub.MovieHubViewModel/a\\
import ${PACKAGE_NAME}.${SCREEN_NAME_PASCAL}Screen\\
import ${PACKAGE_NAME}.${SCREEN_NAME_PASCAL}ViewModel" "$NAVIGATION_ROOT_FILE"
        echo -e "${GREEN}✓ Added imports to NavigationRoot.kt${NC}"
    fi

    # Add navigation case
    if ! grep -q "is Screen.$SCREEN_NAME_PASCAL" "$NAVIGATION_ROOT_FILE"; then
        sed -i '' "/is Screen.Dashboard -> {/,/}/a\\
\\
                is Screen.$SCREEN_NAME_PASCAL -> {\\
                    NavEntry(key = key) {\\
                        val viewModel: ${SCREEN_NAME_PASCAL}ViewModel = hiltViewModel()\\
                        ${SCREEN_NAME_PASCAL}Screen(\\
                            onNavigateBack = { backStack.removeLastOrNull() },\\
                            viewModel = viewModel\\
                        )\\
                    }\\
                }" "$NAVIGATION_ROOT_FILE"
        echo -e "${GREEN}✓ Added navigation case to NavigationRoot.kt${NC}"
    else
        echo -e "${RED}✗ Navigation case for $SCREEN_NAME_PASCAL already exists${NC}"
    fi
else
    echo -e "${RED}✗ NavigationRoot.kt not found at $NAVIGATION_ROOT_FILE${NC}"
fi

echo ""
echo -e "${GREEN}🎉 Screen generation completed successfully!${NC}"
echo ""
echo -e "${BLUE}Generated files:${NC}"
echo -e "  • $SCREEN_DIR/${SCREEN_NAME_PASCAL}Screen.kt"
echo -e "  • $SCREEN_DIR/${SCREEN_NAME_PASCAL}ViewModel.kt"
echo -e "  • $SCREEN_DIR/events/${SCREEN_NAME_PASCAL}Events.kt"
echo -e "  • $SCREEN_DIR/states/${SCREEN_NAME_PASCAL}States.kt"
echo ""
echo -e "${BLUE}Updated files:${NC}"
echo -e "  • $NAVIGATION_PATH/Screen.kt"
echo -e "  • $NAVIGATION_PATH/NavigationRoot.kt"
echo ""
echo -e "${BLUE}Next steps:${NC}"
echo -e "  1. Build your project to ensure everything compiles"
echo -e "  2. Add your business logic to ${SCREEN_NAME_PASCAL}ViewModel"
echo -e "  3. Customize the UI in ${SCREEN_NAME_PASCAL}Screen"
echo -e "  4. Add navigation to this screen from other screens"
echo ""