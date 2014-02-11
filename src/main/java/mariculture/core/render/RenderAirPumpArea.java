package mariculture.core.render;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;

public class RenderAirPumpArea extends RenderBase {
	public RenderAirPumpArea(RenderBlocks render) {
		super(render);
	}

	@Override
	public void renderBlock() {
		setTexture(Blocks.anvil);
		renderBlock(0, 0, 0, 1, 1, 1);
	}
}
