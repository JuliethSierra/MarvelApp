import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.data.api.models.SeriesID

data class SeriesUiState(
    val listCharacters: List<Character> = emptyList(),
    val character: Character? = null,
    val series: List<SeriesID> = emptyList(),
    val isCharacterLoading: Boolean = true,
    val isSeriesListLoading: Boolean = true,
)
