import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var viewModel = ViewModel()
	let greet = Greeting().greet()

	var body: some View {
        Text(viewModel.text)
            .bold()
	}
}

extension ContentView {
    class ViewModel: ObservableObject {
        @Published var text: String = "Loading..."
        
        init() {
            Service().getDoc { doc, error in
                DispatchQueue.main.async {
                    if let doc = doc {
                        self.text = doc
                    } else {
                        self.text = error?.localizedDescription ?? "error"
                    }
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
