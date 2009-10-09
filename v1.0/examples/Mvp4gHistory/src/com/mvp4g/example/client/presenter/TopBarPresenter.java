package com.mvp4g.example.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.mvp4g.client.presenter.Presenter;
import com.mvp4g.example.client.EventsEnum;
import com.mvp4g.example.client.ServiceAsync;
import com.mvp4g.example.client.bean.BasicBean;
import com.mvp4g.example.client.bean.DealBean;
import com.mvp4g.example.client.bean.ProductBean;
import com.mvp4g.example.client.presenter.view_interface.TopBarViewInterface;

public class TopBarPresenter extends Presenter<TopBarViewInterface> {

	private ServiceAsync service = null;

	private ProductBean productSelected = null;
	private DealBean dealSelected = null;

	public void bind() {
		view.getShowDealButton().addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {
				ListBox listBox = view.getDealList();
				String dealId = listBox.getValue( listBox.getSelectedIndex() );
				service.getDealDetails( dealId, new AsyncCallback<DealBean>() {

					public void onFailure( Throwable caught ) {
						//do sthg						
					}

					public void onSuccess( DealBean deal ) {
						dealSelected = deal;
						eventBus.dispatch( EventsEnum.DISPLAY_DEAL, deal, view.getSave().getValue().booleanValue() );
					}

				} );
			}

		} );

		view.getShowProductButton().addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {
				ListBox listBox = view.getProductList();
				String productId = listBox.getValue( listBox.getSelectedIndex() );
				service.getProductDetails( productId, new AsyncCallback<ProductBean>() {

					public void onFailure( Throwable caught ) {
						//do sthg						
					}

					public void onSuccess( ProductBean product ) {
						productSelected = product;
						eventBus.dispatch( EventsEnum.DISPLAY_PRODUCT, product, view.getSave().getValue().booleanValue() );
					}

				} );
			}

		} );

	}

	public void onStart() {
		service.getDeals( new AsyncCallback<List<BasicBean>>() {

			public void onFailure( Throwable caught ) {
				//do sthg				
			}

			public void onSuccess( List<BasicBean> deals ) {
				service.getProducts( new AsyncCallback<List<BasicBean>>() {

					public void onFailure( Throwable caught ) {
						//do sthg				
					}

					public void onSuccess( List<BasicBean> products ) {
						ListBox productsBox = view.getProductList();
						fillListBox( productsBox, products );
						if ( productSelected != null ) {
							selectRightIndex( productsBox, productSelected.getId() );
						}
						eventBus.dispatch( EventsEnum.CHANGE_TOP_WIDGET, view );
					}

				} );

				ListBox dealsBox = view.getDealList();
				fillListBox( dealsBox, deals );
				if ( dealSelected != null ) {
					selectRightIndex( dealsBox, dealSelected.getId() );
				}

			}

		} );
	}

	public void setService( ServiceAsync service ) {
		this.service = service;
	}

	public void onDisplayDeal( DealBean bean ) {
		if ( ( bean != null ) && ( !bean.equals( dealSelected ) ) ) {
			selectRightIndex( view.getDealList(), bean.getId() );
			dealSelected = bean;
		}
	}

	public void onDisplayProduct( ProductBean bean ) {
		if ( ( bean != null ) && ( !bean.equals( productSelected ) ) ) {
			selectRightIndex( view.getProductList(), bean.getId() );
			productSelected = bean;
		}
	}

	public void onInit() {
		view.getDealList().setSelectedIndex( 0 );
		view.getProductList().setSelectedIndex( 0 );
	}

	private void fillListBox( ListBox listBox, List<BasicBean> list ) {

		int size = list.size();
		BasicBean bean = null;

		for ( int i = 0; i < size; i++ ) {
			bean = list.get( i );
			listBox.addItem( bean.getName(), bean.getId() );
		}

	}

	private void selectRightIndex( ListBox listBox, String value ) {
		int size = listBox.getItemCount();
		for ( int index = 0; index < size; index++ ) {
			if ( listBox.getValue( index ).equals( value ) ) {
				listBox.setSelectedIndex( index );
				break;
			}
		}
	}

}